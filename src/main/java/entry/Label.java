package entry;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * Created by wsdevotion on 16/1/30.
 */
@Table("lable_table")
public class Label {

    @Id
    private int lable_id;

    @Column
    private String lable_img;

    @Column
    private String lable_name;

    //lable描述
    @Column
    private String lable_slogan;

    //对应article_talbe -> article_id,貌似只有一个，暂定text，方便以后扩展
    @Column
    private String lable_article_id;

    @Column
    private long lable_follow_number;

    @Column
    private String lable_pub_time;

    public int getLable_id() {
        return lable_id;
    }

    public void setLable_id(int lable_id) {
        this.lable_id = lable_id;
    }

    public String getLable_img() {
        return lable_img;
    }

    public void setLable_img(String lable_img) {
        this.lable_img = lable_img;
    }

    public String getLable_name() {
        return lable_name;
    }

    public void setLable_name(String lable_name) {
        this.lable_name = lable_name;
    }

    public String getLable_slogan() {
        return lable_slogan;
    }

    public void setLable_slogan(String lable_slogan) {
        this.lable_slogan = lable_slogan;
    }

    public String getLable_article_id() {
        return lable_article_id;
    }

    public void setLable_article_id(String lable_article_id) {
        this.lable_article_id = lable_article_id;
    }

    public long getLable_follow_number() {
        return lable_follow_number;
    }

    public void setLable_follow_number(long lable_follow_number) {
        this.lable_follow_number = lable_follow_number;
    }

    public String getLable_pub_time() {
        return lable_pub_time;
    }

    public void setLable_pub_time(String lable_pub_time) {
        this.lable_pub_time = lable_pub_time;
    }
}
