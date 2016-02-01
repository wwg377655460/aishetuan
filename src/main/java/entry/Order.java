package entry;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.Table;

/**
 * Created by wsdevotion on 16/2/1.
 */
@Table("order_table")
public class Order {

    @Id
    private int order_id;

    //订单状态0众筹成功 1付款 2发货 3确认收货
    @Column
    private int order_status;

    @Column
    private String order_name;

    @Column
    private int order_project_id;

    @One(target = Project.class, field = "order_project_id")
    private Project project;

    @Column
    private int order_payed_money;

    @Column
    private String order_number;

    @Column
    private String order_logistics_id;

    @Column
    private String order_pub_time;

    @Column
    private String order_user_name;

    @Column
    private String order_user_phone;

    @Column
    private String order_user_address;

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getOrder_status() {
        return order_status;
    }

    public void setOrder_status(int order_status) {
        this.order_status = order_status;
    }

    public String getOrder_name() {
        return order_name;
    }

    public void setOrder_name(String order_name) {
        this.order_name = order_name;
    }

    public int getOrder_project_id() {
        return order_project_id;
    }

    public void setOrder_project_id(int order_project_id) {
        this.order_project_id = order_project_id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public int getOrder_payed_money() {
        return order_payed_money;
    }

    public void setOrder_payed_money(int order_payed_money) {
        this.order_payed_money = order_payed_money;
    }

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public String getOrder_logistics_id() {
        return order_logistics_id;
    }

    public void setOrder_logistics_id(String order_logistics_id) {
        this.order_logistics_id = order_logistics_id;
    }

    public String getOrder_pub_time() {
        return order_pub_time;
    }

    public void setOrder_pub_time(String order_pub_time) {
        this.order_pub_time = order_pub_time;
    }

    public String getOrder_user_name() {
        return order_user_name;
    }

    public void setOrder_user_name(String order_user_name) {
        this.order_user_name = order_user_name;
    }

    public String getOrder_user_phone() {
        return order_user_phone;
    }

    public void setOrder_user_phone(String order_user_phone) {
        this.order_user_phone = order_user_phone;
    }

    public String getOrder_user_address() {
        return order_user_address;
    }

    public void setOrder_user_address(String order_user_address) {
        this.order_user_address = order_user_address;
    }
}
