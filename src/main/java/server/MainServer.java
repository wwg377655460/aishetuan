package server;

import dao.*;
import entry.Address;
import entry.Banner;

/**
 * Created by wsdevotion on 16/1/30.
 */
public class MainServer {

    //用户实体数据操作
    private static UserDao userDao;

    //轮播实体数据操作
    private static BannerDao bannerDao;

    //标签实体数据操作
    private static LabelDao labelDao;

    //项目实体数据操作
    private static ProjectDao projectDao;

    //文章实体数据操作
    private static ArticleDao articleDao;

    //评论实体数据操作
    private static CommentDao commentDao;

    //回复实体数据操作
    private static ReplyDao replyDao;

    //信息实体数据操作
    private static InfoDao infoDao;

    //订单实体数据操作
    private static OrderDao orderDao;

    //地址实体数据操作
    private static AddressDao addressDao;

    public static UserDao getUserDao(){
        if(null == userDao){
            userDao = new UserDao();
        }
        return userDao;
    }

    public static BannerDao getBannerDao(){
        if(null == bannerDao){
            bannerDao = new BannerDao();
        }
        return bannerDao;
    }

    public static LabelDao getLabelDao(){
        if(null == labelDao){
            labelDao = new LabelDao();
        }
        return labelDao;
    }

    public static ProjectDao getProjectDao(){
        if(null == projectDao){
            projectDao = new ProjectDao();
        }
        return projectDao;
    }

    public static ArticleDao getArticleDao(){
        if(null == articleDao){
            articleDao = new ArticleDao();
        }
        return articleDao;
    }

    public static CommentDao getCommentDao(){
        if(null == commentDao){
            commentDao = new CommentDao();
        }
        return commentDao;
    }

    public static ReplyDao getReplyDao(){
        if(null == replyDao){
            replyDao = new ReplyDao();
        }
        return replyDao;
    }

    public static InfoDao getInfoDao(){
        if(null == infoDao){
            infoDao = new InfoDao();
        }
        return infoDao;
    }

    public static OrderDao getOrderDao(){
        if(null == orderDao){
            orderDao = new OrderDao();
        }
        return orderDao;
    }

    public static AddressDao getAddressDao(){
        if(null == addressDao){
            addressDao = new AddressDao();
        }
        return addressDao;
    }
}
