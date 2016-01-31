package entry;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * Created by wsdevotion on 16/1/30.
 */
@Table("banner_table")
public class Banner {

    @Id
    private int banner_id;

    @Column
    private String banner_img;

    @Column
    private String banner_name;

    @Column
    private String banner_url;

    @Column
    private String banner_pub_time;


    public int getBanner_id() {
        return banner_id;
    }

    public void setBanner_id(int banner_id) {
        this.banner_id = banner_id;
    }

    public String getBanner_img() {
        return banner_img;
    }

    public void setBanner_img(String banner_img) {
        this.banner_img = banner_img;
    }

    public String getBanner_name() {
        return banner_name;
    }

    public void setBanner_name(String banner_name) {
        this.banner_name = banner_name;
    }

    public String getBanner_url() {
        return banner_url;
    }

    public void setBanner_url(String banner_url) {
        this.banner_url = banner_url;
    }

    public String getBanner_pub_time() {
        return banner_pub_time;
    }

    public void setBanner_pub_time(String banner_pub_time) {
        this.banner_pub_time = banner_pub_time;
    }
}
