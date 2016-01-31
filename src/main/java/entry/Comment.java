package entry;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.Table;

import java.util.List;

/**
 * Created by wsdevotion on 16/1/23.
 */
@Table("comments_table")
public class Comment {

    @Id
    private int comment_id;

    @Column
    private int comment_author_id;

    @One(target = User.class, field = "comment_author_id")
    private User user_main;

    @Column
    private int comment_to_project_id;

    @Column
    private String comment_content;

    //是否有回复
    @Column
    private int comment_reply_if;

    @Column
    private int comment_support_num;

    @Column
    private String comment_pub_time;

    private List<Reply> reply_list;


    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public int getComment_author_id() {
        return comment_author_id;
    }

    public void setComment_author_id(int comment_author_id) {
        this.comment_author_id = comment_author_id;
    }

    public User getUser_main() {
        return user_main;
    }

    public void setUser_main(User user_main) {
        this.user_main = user_main;
    }

    public int getComment_to_project_id() {
        return comment_to_project_id;
    }

    public int getComment_reply_if() {
        return comment_reply_if;
    }

    public void setComment_reply_if(int comment_reply_if) {
        this.comment_reply_if = comment_reply_if;
    }

    public void setComment_to_project_id(int comment_to_project_id) {
        this.comment_to_project_id = comment_to_project_id;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public List<Reply> getReply_list() {
        return reply_list;
    }

    public void setReply_list(List<Reply> reply_list) {
        this.reply_list = reply_list;
    }

    public int getComment_support_num() {
        return comment_support_num;
    }

    public void setComment_support_num(int comment_support_num) {
        this.comment_support_num = comment_support_num;
    }

    public String getComment_pub_time() {
        return comment_pub_time;
    }

    public void setComment_pub_time(String comment_pub_time) {
        this.comment_pub_time = comment_pub_time;
    }
}
