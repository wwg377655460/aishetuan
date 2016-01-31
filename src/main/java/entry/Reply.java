package entry;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.Table;

/**
 * Created by wsdevotion on 16/1/31.
 */
@Table("reply_table")
public class Reply {

    @Id
    private int reply_id;

    @Column
    private String reply_content;

    @Column
    private int reply_comment_id;

    @Column
    private String reply_time;

    @Column
    private String reply_author_name;

    @Column
    private int reply_author_id;

    @One(target = User.class, field = "reply_author_id")
    private User user;


    public int getReply_id() {
        return reply_id;
    }

    public void setReply_id(int reply_id) {
        this.reply_id = reply_id;
    }

    public String getReply_content() {
        return reply_content;
    }

    public void setReply_content(String reply_content) {
        this.reply_content = reply_content;
    }

    public int getReply_comment_id() {
        return reply_comment_id;
    }

    public void setReply_comment_id(int reply_comment_id) {
        this.reply_comment_id = reply_comment_id;
    }

    public String getReply_author_name() {
        return reply_author_name;
    }

    public void setReply_author_name(String reply_author_name) {
        this.reply_author_name = reply_author_name;
    }

    public String getReply_time() {
        return reply_time;
    }

    public void setReply_time(String reply_time) {
        this.reply_time = reply_time;
    }

    public int getReply_author_id() {
        return reply_author_id;
    }

    public void setReply_author_id(int reply_author_id) {
        this.reply_author_id = reply_author_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
