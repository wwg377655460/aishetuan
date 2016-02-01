package server;

import entry.*;
import exception.AuthException;
import redis.clients.jedis.Jedis;
import spark.Request;
import spark.Response;
import util.ImageUtils;
import util.Md5;
import util.RedisFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Created by wsdevotion on 16/1/24.
 */
public class UserServer extends MainServer {

    /**
     * 登录
     * @param user_phone 电话号码
     * @param user_password 密码
     * @return 用户登录信息效验字段
     * @throws AuthException 用户信息不正确
     */
    public String login(String user_phone, String user_password) throws AuthException {
        //判断用户名密码是否正确
        User user = getUserDao().getByUserPhone(user_phone);
        //用户名不存在
        if (null == user) {
            throw new AuthException("-1");
        }
        if (!user.getUser_password().equals(Md5.MD5_Secure(user_password))) {
            throw new AuthException("-2");
        }

        //生成access_token，放进redis
        String access_token = Md5.MD5_Secure(user_phone);
        Jedis jedis = RedisFactory.get();
        if (!jedis.exists(access_token)) {
            jedis.setex(access_token, 1000 * 60 * 60 * 24, user_phone);
        }
        if (jedis != null) {
            jedis.close();
        }

        return access_token;
    }

    /**
     * 注册
     * @param user 用户信息实体
     * @throws AuthException 用户信息已经注册
     */
    public void register(User user) throws AuthException {
        //判断用户是否已经注册
        User usernow = getUserDao().getByUserPhone(user.getUser_phone());
        if (usernow != null) {
            throw new AuthException("-1");
        }


//        //验证字段信息
//        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//        Validator validator = factory.getValidator();
//        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
//        if (constraintViolations.size() != 0) {
//            throw new AuthException("-1");
//        }

        //保存用户头像
        if (!"".equals(user.getUser_head_img()) && user.getUser_head_img() != null) {
            String image_name = UUID.randomUUID() + ".jpg";
            ImageUtils.GenerateImage(user.getUser_head_img(), image_name);
            user.setUser_head_img(image_name);
        }

        //哈希密码
        user.setUser_password(Md5.MD5_Secure(user.getUser_password()));


        getUserDao().insert(user);
    }

    /**
     * 判断用户是否关注标签
     * @param id 标签id
     * @param user_phone 电话
     * @return 1 关注 -1 没有关注 -2 找不到对应的标签信息
     * @throws AuthException 字段格式不正确
     */
    public String ifattach(String id, String user_phone) throws AuthException {
        //判断数据格式
        boolean isNum = id.matches("[0-9]+");
        boolean isUserPhone = user_phone.matches("[0-9]{11}");
        if (!isNum || !isUserPhone) {
            throw new AuthException("-1");
        }
        //转成整形
        int idn = Integer.parseInt(id);
        //查询对应的label
        Label label = getLabelDao().get(idn);
        if (label == null) {
            return "-2";
        }
        //获取用户信息
        User user = getUserDao().getByUserPhone(user_phone);
        //用","隔开关注标签的id
        if (user.getUser_followed_lable_ids() != null) {
            if (user.getUser_followed_lable_ids().indexOf(id) != -1) {
                return "1";
            }
        }
        return "-1";
    }

    /**
     * 关注标签
     * @param id 标签id
     * @param user_phone 电话号码
     * @return 1 关注成功 2 取消关注 -1 找不到对应的信息
     * @throws AuthException 参数格式错误
     */
    public String attach(String id, String user_phone) throws AuthException {
        //判断数据格式
        boolean isNum = id.matches("[0-9]+");
        boolean isUserPhone = user_phone.matches("[0-9]{11}");
        if (!isNum || !isUserPhone) {
            throw new AuthException("-1");
        }
        //转成整形
        int idn = Integer.parseInt(id);
        //查询对应的label
        Label label = getLabelDao().get(idn);
        if (label == null) {
            return "-1";
        }
        //获取用户信息
        User user = getUserDao().getByUserPhone(user_phone);
        //用","隔开关注标签的id
        int flag = 0;
        String ids = user.getUser_followed_lable_ids();
        if (ids != null && (flag = ids.indexOf(id)) != -1) {
            //取消关注
            String follow_ids;
            if (flag == 0) {
                follow_ids = ids.substring(id.length() + 1);
            } else if (ids.indexOf(",") != -1) {
                follow_ids = ids.substring(0, flag - 1) + ids.substring(flag + id.length());
            } else {
                follow_ids = "";
            }
            //减小关注人数
            int follow_num = (int)label.getLable_follow_number() - 1;
            //更新user和label
            getUserDao().updateUserAndLabel(user.getUser_id(), label.getLable_id(), follow_ids, follow_num);
            return "2";

        }
        //增加关注
        String follow_ids = "";
        if (ids == null || "".equals(ids)){
            follow_ids = label.getLable_id() + "";
        }else {
            follow_ids = ids + "," + label.getLable_id();
        }
        int follow_num = (int)label.getLable_follow_number() + 1;
        getUserDao().updateUserAndLabel(user.getUser_id(), label.getLable_id(), follow_ids, follow_num);
        return "1";

    }

    /**
     * 判断用户是否喜欢项目
     * @param id 标签id
     * @param user_phone 电话
     * @return 1 喜欢 -1 没有喜欢 -2 找不到对应的项目信息
     * @throws AuthException 字段格式不正确
     */
    public String islike(String id, String user_phone) throws AuthException {
        //判断数据格式
        boolean isNum = id.matches("[0-9]+");
        boolean isUserPhone = user_phone.matches("[0-9]{11}");
        if (!isNum || !isUserPhone) {
            throw new AuthException("-1");
        }
        //转成整形
        int idn = Integer.parseInt(id);
        //查询对应的label
        Project project = getProjectDao().get(idn);
        if (project == null) {
            return "-2";
        }
        //获取用户信息
        User user = getUserDao().getByUserPhone(user_phone);
        //用","隔开关注标签的id
        if (user.getUser_liked_project_ids() != null) {
            if (user.getUser_liked_project_ids().indexOf(id) != -1) {
                return "1";
            }
        }
        return "-1";
    }

    /**
     * 喜欢项目
     * @param id 标签id
     * @param user_phone 电话号码
     * @return 1 喜欢成功 2 取消喜欢 -1 找不到对应的信息
     * @throws AuthException 参数格式错误
     */
    public String like(String id, String user_phone) throws AuthException {
        //判断数据格式
        boolean isNum = id.matches("[0-9]+");
        boolean isUserPhone = user_phone.matches("[0-9]{11}");
        if (!isNum || !isUserPhone) {
            throw new AuthException("-1");
        }
        //转成整形
        int idn = Integer.parseInt(id);
        //查询对应的label
        Project project = getProjectDao().get(idn);
        if (project == null) {
            return "-1";
        }
        //获取用户信息
        User user = getUserDao().getByUserPhone(user_phone);
        //用","隔开关注标签的id
        int flag = 0;
        String ids = user.getUser_liked_project_ids();
        if (ids != null && (flag = ids.indexOf(id)) != -1) {
            //取消关注
            String follow_ids;
            if (flag == 0){
                follow_ids = ids.substring(id.length() + 1);
            }else if (ids.indexOf(",") != -1) {
                follow_ids = ids.substring(0, flag-1) + ids.substring(flag + id.length());
            } else {
                follow_ids = "";
            }
            //减小关注人数
            int follow_num = project.getProject_like_number() - 1;
            //更新user和label
            user.setUser_followed_lable_ids(follow_ids);

            getUserDao().updateUserAndProject(user.getUser_id(), project.getProject_id(), follow_ids, follow_num);
            return "2";

        }
        //增加关注
        String follow_ids = "";
        if (ids == null || "".equals(ids)){
            follow_ids = project.getProject_id() + "";
        }else {
            follow_ids = ids + "," + project.getProject_id();
        }
        int follow_num = project.getProject_like_number() + 1;
        getUserDao().updateUserAndLabel(user.getUser_id(), project.getProject_id(), follow_ids, follow_num);
        return "1";
    }

    public User getUserById(int id) {
        return getUserDao().get(id);
    }

    //通过电话获取用户信息
    public User getUserByPhone(String user_phone) throws AuthException {
        //判断数据格式是否正确
        boolean isUserPhone = user_phone.matches("[0-9]{11}");
        if (!isUserPhone) {
            throw new AuthException("-1");
        }
        //获取用户信息
        return getUserDao().getByUserPhone(user_phone);
    }

    //更新用户信息
    public String updateUserMes(User user) throws AuthException {
        //判断数据格式是否正确
        boolean isUserPhone = user.getUser_phone().matches("[0-9]{11}");
        if (!isUserPhone) {
            throw new AuthException("-1");
        }
        //获取用户信息
        User user1 = getUserDao().getByUserPhone(user.getUser_phone());
        if (user1 == null) {
            return "-1";
        }
        //保存用户头像
        if (!"".equals(user.getUser_head_img()) && user.getUser_head_img() != null) {
            String image_name = UUID.randomUUID() + ".jpg";
            ImageUtils.GenerateImage(user.getUser_head_img(), image_name);
            user1.setUser_head_img(image_name);
        }

        //更新信息
        user1.setUser_name(user.getUser_name());
        if (!"".equals(user.getUser_password()) && user.getUser_password()!=null) {
            user1.setUser_password(Md5.MD5_Secure(user.getUser_password()));
        }
        user1.setUser_age(user.getUser_age());
        user1.setUser_sex(user.getUser_sex());

        getUserDao().updateUser(user1);
        return "1";
    }

    //获取用户关注label信息
    public List<Label> getLabelsByUserPhone(String user_phone) throws AuthException {
        //判断数据格式是否正确
        boolean isUserPhone = user_phone.matches("[0-9]{11}");
        if (!isUserPhone) {
            throw new AuthException("-1");
        }
        //获取用户信息
        User user = getUserDao().getByUserPhone(user_phone);
        if (user == null) {
            return null;
        }
        //获取用户关注label信息
        String [] labels = null;
        if (user.getUser_followed_lable_ids() != null && !"".equals(user.getUser_followed_lable_ids())){
            labels = user.getUser_followed_lable_ids().split("[,]");
        } else {
            return null;
        }
        List<Label> list = new ArrayList<>();
        for (String id : labels) {
            //获取对应的label信息
            Label label = getLabelDao().get(Integer.parseInt(id));
            list.add(label);
        }
        return list;
    }

    //获取用户订单信息
    public List<Order> getOrderByUserPhone(String user_phone) throws AuthException {
        //判断数据格式是否正确
        boolean isUserPhone = user_phone.matches("[0-9]{11}");
        if (!isUserPhone) {
            throw new AuthException("-1");
        }
        //获取用户信息
        User user = getUserDao().getByUserPhone(user_phone);
        if (user == null) {
            return null;
        }
        //获取用户关注label信息
        String [] orders = user.getUser_ordered_ids().split("[,]");
        List<Order> list = new ArrayList<>();
        for (String id : orders) {
            //获取对应的label信息
            Order order = getOrderDao().get(Integer.parseInt(id));
            list.add(order);
        }
        return list;
    }

    //获取订单信息
    public Order getOrderById(String user_phone, String order_id) throws AuthException {
        //判断数据格式
        boolean isNum = order_id.matches("[0-9]+");
        boolean isUserPhone = user_phone.matches("[0-9]{11}");
        if (!isNum || !isUserPhone) {
            throw new AuthException("-1");
        }
        //转成整形
        int idn = Integer.parseInt(order_id);
        //获取订单信息
        Order order = getOrderDao().get(idn);
        if (order == null || !order.getOrder_user_phone().equals(user_phone)) {
            return null;
        }

        return order;
    }

    //通过电话获取用户地址
    public List<Address> getAddressesByUserPhone(String user_phone) throws AuthException {
        //判断数据格式是否正确
        boolean isUserPhone = user_phone.matches("[0-9]{11}");
        if (!isUserPhone) {
            throw new AuthException("-1");
        }
        //获取用户信息
        User user = getUserDao().getByUserPhone(user_phone);
        if (user == null) {
            return null;
        }
        //获取地址信息
        return getAddressDao().getAddressesByUserId(user.getUser_id());
    }

    //添加地址信息
    public String insertAddress(Address address, String user_phone) throws AuthException {
        //判断数据格式是否正确
        boolean isUserPhone = user_phone.matches("[0-9]{11}");
        if (!isUserPhone) {
            throw new AuthException("-1");
        }
        //获取用户信息
        User user = getUserDao().getByUserPhone(user_phone);
        if (user == null) {
            return "-1";
        }
        address.setAddress_user_id(user.getUser_id());
        getAddressDao().insertAddress(address);
        return "1";
    }

    public List<Info> getInfoByUserPhone(String user_phone) throws AuthException {
        //判断数据格式是否正确
        boolean isUserPhone = user_phone.matches("[0-9]{11}");
        if (!isUserPhone) {
            throw new AuthException("-1");
        }
        //获取用户信息
        User user = getUserDao().getByUserPhone(user_phone);
        if (user == null) {
            return null;
        }
        List<Info> list = getInfoDao().getInfoByUserId(user.getUser_id());
        //info标示为已读
        getInfoDao().updateInfoStatus(user.getUser_id());
        return list;
    }
}
