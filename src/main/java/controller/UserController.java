package controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import entry.*;
import exception.AuthException;
import exception.NullRequestException;
import org.apache.log4j.Logger;
import org.nutz.json.Json;
import server.*;
import util.RedisUtil;


import java.util.List;

import static spark.Spark.*;

/**
 * Created by wsdevotion on 16/1/22.
 */
public class UserController extends BaseController {


    public static void main(String[] args) {

        UserServer userServer = new UserServer();
        BannerServer bannerServer = new BannerServer();
        LabelsServer labelsServer = new LabelsServer();
        ProjectServer projectServer = new ProjectServer();
        CommentServer commentServer = new CommentServer();

        RedisUtil.initRedis();

        get("hello", ((request, response) -> {
            Logger logger = Logger.getLogger(UserController.class);
            // 记录debug级别的信息
            logger.debug("This is debug message.");
            // 记录info级别的信息
            logger.info("This is info message.");
            // 记录error级别的信息
            logger.error("This is error message.");
            response.body();
            return "hello 123";
        }));

        //登录
        post("/login", ((request, response) -> {

            //获取传输的信息
            JSONObject jsonObject = parseJson(request);
            String user_phone = getStrOrDie(jsonObject, "user_phone");
            String user_password = getStrOrDie(jsonObject, "user_password");
            String access_token = userServer.login(user_phone, user_password);
            //返回access_token
            String returnMes = returnMes(1, "access_token", access_token);
            return returnMes;

        }));

        //注册
        post("/register", ((request, response) -> {

            //获取传输的信息
            JSONObject jsonObject = parseJson(request);
            String user_name = getStrOrDie(jsonObject, "user_name");
            String user_phone = getStrOrDie(jsonObject, "user_phone");
            String user_password = getStrOrDie(jsonObject, "user_password");
            String user_sex = getStrOrDie(jsonObject, "user_sex");
            int user_age = getIntOrDie(jsonObject, "user_age");
            String user_head_img = getStrOrDie(jsonObject, "user_head_img");
            //生成User对象
            User user = JSON.parseObject(jsonObject.toString(), User.class);
            user.setUser_reg_time(System.currentTimeMillis() + "");
            //调用userServer注册用户
            userServer.register(user);
            return returnStatusMes("1");
        }));

        //获取轮播图片信息
        get("/mainImage", ((request, response) -> {

            //调用Server获取所有轮播图片信息
            List<Banner> list = bannerServer.getAll();
            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            for (Banner banner : list) {
                jsonObject.put("image", banner.getBanner_img());
                jsonArray.add(jsonObject);
                //解决json多了"的问题
                jsonArray = JSONArray.parseArray(jsonArray.toString());
                jsonObject.clear();
            }
            jsonObject.put("status", 1);
            jsonObject.put("mainImage", jsonArray);
            return parseJsonObjectToString(jsonObject);
        }));

        //获取按热度排名的标签
        get("/labels/:start_num/:num", ((request, response) -> {

            //获取开始的位置
            String start_num = request.params(":start_num");
            //获取要获取的数目
            String num = request.params(":num");
            //调用Server获取信息
            List<Label> list = labelsServer.getLabelsByFollowNum(start_num, num);
            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            for (Label label : list) {
                jsonObject.put("lable_id", label.getLable_id());
                jsonObject.put("lable_name", label.getLable_name());
                jsonObject.put("lable_img", label.getLable_img());
                jsonArray.add(jsonObject);
                jsonArray = JSONArray.parseArray(jsonArray.toString());
                jsonObject.clear();
            }
            jsonObject.put("status", 1);
            jsonObject.put("data", jsonArray);
            return jsonObject.toJSONString();
        }));

        //获取按顺序项目信息
        get("/item/:page", ((request, response) -> {
            //获取页数
            String page = request.params(":page");

            //调用Server获取信息
            List<Project> list = projectServer.getProjectByPage(page);
            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            long time = 0;
            for (Project project : list) {
                time = Long.parseLong(project.getProject_end_time()) - Long.parseLong(project.getProject_pub_time());
                jsonObject.put("project_id", project.getProject_id());
                jsonObject.put("project_name", project.getProject_name());
                jsonObject.put("project_author", project.getProject_author());
                jsonObject.put("project_money_goal", project.getProject_money_goal());
                jsonObject.put("project_money_now", project.getProject_money_now());
                jsonObject.put("project_image", project.getProject_art_img());
                jsonObject.put("time", time);
                jsonArray.add(jsonObject);
                jsonArray = JSONArray.parseArray(jsonArray.toString());
                jsonObject.clear();
            }
            jsonObject.put("status", 1);
            jsonObject.put("data", jsonArray);
            return jsonObject.toJSONString();
        }));


        //获取标签信息
        get("/label/:id", ((request, response) -> {
            String id = request.params(":id");
            return labelsServer.getLabelById(id);
        }));

        //判断用户是否关注标签
        get("/ifattach/:id/:user_phone", ((request, response) -> {
            String id = request.params(":id");
            String user_phone = request.params(":user_phone");
            String i = userServer.ifattach(id, user_phone);
            return returnStatusMes(i);

        }));


        //用户关注或取消关注
        get("/attach/:id/:user_phone", ((request, response) -> {
            String id = request.params(":id");
            String user_phone = request.params(":user_phone");
            String i = userServer.attach(id, user_phone);
            return returnStatusMes(i);
        }));

        //获取项目信息
        get("/project/:id", ((request, response) -> {
            String id = request.params(":id");
            Project project = projectServer.getProjectById(id);
            JSONObject jsonObject = (JSONObject) JSON.toJSON(project);
            jsonObject.put("status", 1);
            return jsonObject.toJSONString();
        }));

        //判断用户是否喜欢项目
        get("/islike/:id/:user_phone", ((request, response) -> {
            String id = request.params(":id");
            String user_phone = request.params(":user_phone");
            String i = userServer.islike(id, user_phone);
            return returnStatusMes(i);
        }));

        //用户喜欢或取消喜欢
        get("/like/:id/:user_phone", ((request, response) -> {
            String id = request.params(":id");
            String user_phone = request.params(":user_phone");
            String i = userServer.like(id, user_phone);
            return returnStatusMes(i);
        }));

        //获取评论信息
        get("/comment/:id", ((request, response) -> {
            String id = request.params(":id");
            List<Comment> list = commentServer.getCommentsByProjectId(id);
            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            for (Comment comment : list) {
                jsonObject.put("comment_id", comment.getComment_id());
                jsonObject.put("comment_author_name", comment.getUser_main().getUser_name());
                jsonObject.put("comment_author_image", comment.getUser_main().getUser_head_img());
                jsonObject.put("comment_content", comment.getComment_content());
                jsonObject.put("comment_pub_time", comment.getComment_pub_time());
                jsonObject.put("comment_support_num", comment.getComment_support_num());
                //添加评论
                JSONObject jsonObject1 = new JSONObject();
                JSONArray jsonArray1 = new JSONArray();
                if (comment.getReply_list() != null) {
                    for (Reply reply : comment.getReply_list()) {
                        jsonObject1.put("comment_reply_to_content", reply.getReply_content());
                        jsonObject1.put("comment_reply_name", reply.getReply_author_name());
                        jsonArray1.add(jsonObject1);
                        jsonArray1 = JSONArray.parseArray(jsonArray1.toString());
                        jsonObject1.clear();
                    }
                }
                jsonObject.put("comment_reply_mes", jsonArray1);
                jsonArray.add(jsonObject);
                jsonArray = JSONArray.parseArray(jsonArray.toString());
                jsonObject.clear();
            }
            jsonObject.put("status", 1);
            jsonObject.put("comment", jsonArray);
            return jsonObject.toJSONString();
        }));

        //发布评论
        post("/comment/:id", ((request, response) -> {
            String id = request.params(":id");
            //获取传输的信息
            JSONObject jsonObject = parseJson(request);
            String comment_user_phone = getStrOrDie(jsonObject, "comment_user_phone");
            String comment_content = getStrOrDie(jsonObject, "comment_content");
            //判断用户身份
            boolean authUser = authUserMessage(request,comment_user_phone);
            if (!authUser) {
                throw new AuthException("-1");
            }
            Comment comment = new Comment();
            comment.setComment_content(comment_content);
            User user = new User();
            user.setUser_phone(comment_user_phone);
            comment.setUser_main(user);
            //调用Server查询评论
            String i = commentServer.insertComment(comment, id);
            return returnStatusMes(i);

        }));

        //回复评论
        put("/comment/:comment_id", ((request, response) -> {
            //评论id
            String comment_id = request.params(":comment_id");
            //获取提交的信息
            JSONObject jsonObject = parseJson(request);
            String comment_reply_name = getStrOrDie(jsonObject, "comment_reply_name");
            String comment_reply_to_content = getStrOrDie(jsonObject, "comment_reply_to_content");
            String comment_reply_phone = getStrOrDie(jsonObject, "comment_reply_phone");
            //判断用户身份
            boolean authUser = authUserMessage(request, comment_reply_phone);
            if (!authUser) {
                throw new AuthException("-1");
            }

            Reply reply = new Reply();
            reply.setReply_author_name(comment_reply_name);
            reply.setReply_content(comment_reply_to_content);
            String i = commentServer.rebackCom(reply, comment_id, comment_reply_phone);
            return returnStatusMes(i);
        }));

        //喜欢评论
        put("/comment_like/:id", ((request, response) -> {
            //评论id
            String comment_id = request.params(":id");
            //获取提交的信息
            JSONObject jsonObject = parseJson(request);
            String user_phone = getStrOrDie(jsonObject, "user_phone");
            //判断用户身份
            boolean authUser = authUserMessage(request, user_phone);
            if (!authUser) {
                throw new AuthException("-1");
            }

            String i = commentServer.likeComment(user_phone, comment_id);
            return returnStatusMes(i);

        }));


        exception(AuthException.class, ((e, request, response) -> {
            response.status(403);
            response.body(returnStatusMes(e.toString().substring(e.toString().indexOf(":") + 1).trim()));
        }));

        exception(NullRequestException.class, ((e, request, response) -> {
            response.status(403);
            response.body(returnStatusMes("-2"));
        }));

        exception(Exception.class, ((e, request, response) -> {
            response.status(500);
            response.body(returnStatusMes("-1"));
        }));
    }
}
