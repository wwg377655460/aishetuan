package server;

import entry.*;
import exception.AuthException;
import spark.Request;

import java.util.List;

/**
 * Created by wsdevotion on 16/1/31.
 */
public class CommentServer extends MainServer {

    public List<Comment> getCommentsByProjectId(String project_id) throws AuthException {
        //判断id类型
        boolean isNum1 = project_id.matches("[0-9]+");
        if (!isNum1) {
            throw new AuthException("-1");
        }
        //转换成整形
        int idn = Integer.parseInt(project_id);
        List<Comment> list =  getCommentDao().getCommentsByProjectId(idn);
        for (int i=0; i<list.size(); i++) {
            //获取author
            list.get(i).setUser_main(getUserDao().get(list.get(i).getComment_author_id()));
            //获取回复信息
            if (list.get(i).getComment_reply_if() == 1) {
                list.get(i).setReply_list(getReplyDao().getReplysByCommentId(list.get(i).getComment_id()));
            }
        }

        return list;
    }

    public String insertComment(Comment comment, String project_id) throws AuthException {
        //判断comment_id类型
        boolean isNum1 = project_id.matches("[0-9]+");
        //判断用户电话格式是否正确
        boolean isUserPhone = comment.getUser_main().getUser_phone().matches("[0-9]{11}");
        if (!isNum1 || !isUserPhone) {
            throw new AuthException("-1");
        }
        //转换成整形
        int idn = Integer.parseInt(project_id);

        //查询用户信息
        User user = getUserDao().getByUserPhone(comment.getUser_main().getUser_phone());
        //查询项目信息
        Project project = getProjectDao().get(idn);
        if (user == null || project == null) {
            return "-1";
        }
        comment.setComment_author_id(user.getUser_id());
        comment.setComment_pub_time(System.currentTimeMillis() + "");
        comment.setComment_to_project_id(idn);
        getCommentDao().insertComment(comment);
        return "1";
    }

    public String
    rebackCom(Reply reply, String comment_id, String user_phone) throws AuthException {
        //判断数据格式
        //判断comment_id类型
        boolean isNum1 = comment_id.matches("[0-9]+");
        //判断用户电话格式是否正确
        boolean isUserPhone = user_phone.matches("[0-9]{11}");
        if (!isNum1 || !isUserPhone) {
            throw new AuthException("-1");
        }
        //转换成整形
        int idn = Integer.parseInt(comment_id);

        //获取评论信息
        Comment comment = getCommentDao().get(idn);
        if (comment == null) {
            return "-1";
        }
        //获取用户信息
        User user = getUserDao().getByUserPhone(user_phone);
        reply.setReply_comment_id(idn);
        reply.setReply_author_id(user.getUser_id());
        reply.setReply_time(System.currentTimeMillis() + "");
        //判断是否有评论
        if (comment.getComment_reply_if() == 0) {
            comment.setComment_reply_if(1);
            getCommentDao().addReplyByComment(comment, reply);
        } else {
            getReplyDao().insert(reply);
        }
        //更改评论用户信息表
        Info info = new Info();
        info.setInfo_comment_id(idn);
        info.setInfo_status(1);//1表示没有读
        info.setInfo_type(1);//1表示评论的回复
        info.setInfo_pub_time(System.currentTimeMillis() + "");
        getInfoDao().insert(info);

        return "1";
    }

    /***
     * 喜欢评论
     * @param user_phone 电话号码
     * @param comment_id 评论id
     * @return 1 赞评论 2 已经赞评论 -1 找不到相应信息
     * @throws AuthException 参数格式错误
     */
    public String likeComment(String user_phone, String comment_id) throws AuthException {
        //判断数据格式
        //判断comment_id类型
        boolean isNum1 = comment_id.matches("[0-9]+");
        //判断用户电话格式是否正确
        boolean isUserPhone = user_phone.matches("[0-9]{11}");
        if (!isNum1 || !isUserPhone) {
            throw new AuthException("-1");
        }
        //转换成整形
        int idn = Integer.parseInt(comment_id);
        //获取用户信息
        User user = getUserDao().getByUserPhone(user_phone);
        //获取评论信息
        Comment comment = getCommentDao().get(idn);
        if (user == null || comment == null) {
            return "-1";
        }


        //用","隔开关注标签的id
        int flag = 0;
        String follow_ids = "";
        String ids = user.getUser_supported_comment_ids();
        if (ids != null || "".equals(ids)) {
            if ((flag = ids.indexOf(comment_id)) != -1) {
                return "0";

            }
            //增加关注
            follow_ids = ids + "," + comment_id;
        } else {
            follow_ids = comment_id;
        }
        int follow_num = comment.getComment_support_num() + 1;
        user.setUser_supported_comment_ids(follow_ids);
        comment.setComment_support_num(follow_num);
        //更新Comment和用户信息
        getCommentDao().updateCommentAndUser(comment, user);
        return "1";
    }
}
