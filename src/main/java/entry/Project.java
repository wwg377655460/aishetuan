package entry;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.Table;

/**
 * Created by wsdevotion on 16/1/30.
 */
@Table("project_table")
public class Project {

    @Id
    private int project_id;

    @Column
    private int project_lable_id;

    @One(target = Label.class, field = "project_lable_id")
    private Label label;

    @Column
    private String project_name;

    @Column
    private String project_author;

    @Column
    private String project_description;

    @Column
    private int project_money_goal;

    @Column
    private int project_money_now;

    @Column
    private int project_like_number;

    @Column
    private String project_pub_time;

    @Column
    private String project_end_time;

    @Column
    private String project_art_img;

    @Column
    private String project_product_img1;

    @Column
    private String project_product_img2;

    @Column
    private String project_product_img3;

    @Column
    private String project_update;

    //0正在、1成功、2关闭（取消）
    @Column
    private int project_status;

    //成功后回报时间
    @Column
    private String project_reply_day;


    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public int getProject_lable_id() {
        return project_lable_id;
    }

    public void setProject_lable_id(int project_lable_id) {
        this.project_lable_id = project_lable_id;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getProject_author() {
        return project_author;
    }

    public void setProject_author(String project_author) {
        this.project_author = project_author;
    }

    public String getProject_description() {
        return project_description;
    }

    public void setProject_description(String project_description) {
        this.project_description = project_description;
    }

    public int getProject_money_goal() {
        return project_money_goal;
    }

    public void setProject_money_goal(int project_money_goal) {
        this.project_money_goal = project_money_goal;
    }

    public int getProject_money_now() {
        return project_money_now;
    }

    public void setProject_money_now(int project_money_now) {
        this.project_money_now = project_money_now;
    }

    public int getProject_like_number() {
        return project_like_number;
    }

    public void setProject_like_number(int project_like_number) {
        this.project_like_number = project_like_number;
    }

    public String getProject_pub_time() {
        return project_pub_time;
    }

    public void setProject_pub_time(String project_pub_time) {
        this.project_pub_time = project_pub_time;
    }

    public String getProject_end_time() {
        return project_end_time;
    }

    public void setProject_end_time(String project_end_time) {
        this.project_end_time = project_end_time;
    }

    public String getProject_art_img() {
        return project_art_img;
    }

    public void setProject_art_img(String project_art_img) {
        this.project_art_img = project_art_img;
    }

    public String getProject_product_img1() {
        return project_product_img1;
    }

    public void setProject_product_img1(String project_product_img1) {
        this.project_product_img1 = project_product_img1;
    }

    public String getProject_product_img2() {
        return project_product_img2;
    }

    public void setProject_product_img2(String project_product_img2) {
        this.project_product_img2 = project_product_img2;
    }

    public String getProject_product_img3() {
        return project_product_img3;
    }

    public void setProject_product_img3(String project_product_img3) {
        this.project_product_img3 = project_product_img3;
    }

    public String getProject_update() {
        return project_update;
    }

    public void setProject_update(String project_update) {
        this.project_update = project_update;
    }

    public int getProject_status() {
        return project_status;
    }

    public void setProject_status(int project_status) {
        this.project_status = project_status;
    }

    public String getProject_reply_day() {
        return project_reply_day;
    }

    public void setProject_reply_day(String project_reply_day) {
        this.project_reply_day = project_reply_day;
    }
}
