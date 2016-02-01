package entry;

import org.nutz.dao.entity.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * Created by wsdevotion on 16/1/23.
 */
@Table("user_table")
public class User {

    @Id
    private int user_id;

    @NotNull
    @Name
    private String user_phone;

    @NotNull
    @Column
    private String user_head_img;

    @NotNull
    @Column
    private String user_name;

    @NotNull
    @Column
    private String user_password;

    @NotNull
    @Column
    private String user_reg_time;

    @NotNull
    @Column
    private int user_sex;

    @Column
    private int user_age;

    @Column
    private String user_followed_lable_ids;

    @Column
    private String user_liked_article_ids;

    @Column
    private String user_liked_project_ids;

    @Column
    private String user_supported_comment_ids;

    @Column
    private String user_ordered_ids;

    @Column
    private String user_address;



    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_head_img() {
        return user_head_img;
    }

    public void setUser_head_img(String user_head_img) {
        this.user_head_img = user_head_img;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public int getUser_age() {
        return user_age;
    }

    public void setUser_age(int user_age) {
        this.user_age = user_age;
    }

    public String getUser_reg_time() {
        return user_reg_time;
    }

    public void setUser_reg_time(String user_reg_time) {
        this.user_reg_time = user_reg_time;
    }

    public int getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(int user_sex) {
        this.user_sex = user_sex;
    }

    public String getUser_followed_lable_ids() {
        return user_followed_lable_ids;
    }

    public void setUser_followed_lable_ids(String user_followed_lable_ids) {
        this.user_followed_lable_ids = user_followed_lable_ids;
    }

    public String getUser_liked_article_ids() {
        return user_liked_article_ids;
    }

    public void setUser_liked_article_ids(String user_liked_article_ids) {
        this.user_liked_article_ids = user_liked_article_ids;
    }

    public String getUser_liked_project_ids() {
        return user_liked_project_ids;
    }

    public void setUser_liked_project_ids(String user_liked_project_ids) {
        this.user_liked_project_ids = user_liked_project_ids;
    }

    public String getUser_supported_comment_ids() {
        return user_supported_comment_ids;
    }

    public void setUser_supported_comment_ids(String user_supported_comment_ids) {
        this.user_supported_comment_ids = user_supported_comment_ids;
    }

    public String getUser_ordered_ids() {
        return user_ordered_ids;
    }

    public void setUser_ordered_ids(String user_ordered_ids) {
        this.user_ordered_ids = user_ordered_ids;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }


}
