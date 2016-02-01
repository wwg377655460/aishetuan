package entry;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.Table;

/**
 * Created by wsdevotion on 16/1/23.
 */
@Table("info_table")
public class Info {

    @Id
    private int info_id;

    @Column
    private String info_pub_time;

    @Column
    private int info_status;

    @Column
    private int info_user_id;

    @Column
    private int info_project_id;

    @Column
    private int info_type;

    @Column
    private int info_comment_id;

    //项目更新内容
    @Column
    private String info_other1;


    public int getInfo_id() {
        return info_id;
    }

    public void setInfo_id(int info_id) {
        this.info_id = info_id;
    }

    public String getInfo_pub_time() {
        return info_pub_time;
    }

    public void setInfo_pub_time(String info_pub_time) {
        this.info_pub_time = info_pub_time;
    }

    public int getInfo_status() {
        return info_status;
    }

    public void setInfo_status(int info_status) {
        this.info_status = info_status;
    }

    public int getInfo_type() {
        return info_type;
    }

    public int getInfo_user_id() {
        return info_user_id;
    }

    public void setInfo_user_id(int info_user_id) {
        this.info_user_id = info_user_id;
    }

    public int getInfo_project_id() {
        return info_project_id;
    }

    public void setInfo_project_id(int info_project_id) {
        this.info_project_id = info_project_id;
    }

    public void setInfo_type(int info_type) {
        this.info_type = info_type;
    }

    public int getInfo_comment_id() {
        return info_comment_id;
    }

    public void setInfo_comment_id(int info_comment_id) {
        this.info_comment_id = info_comment_id;
    }

    public String getInfo_other1() {
        return info_other1;
    }

    public void setInfo_other1(String info_other1) {
        this.info_other1 = info_other1;
    }
}
