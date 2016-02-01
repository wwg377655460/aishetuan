package entry;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * Created by wsdevotion on 16/2/1.
 */
@Table("address_table")
public class Address {

    @Id
    private int address_id;

    @Column
    private String address_phone;

    @Column
    private String address_name;

    @Column
    private String address_local;

    @Column
    private int address_user_id;

    @Column
    private int address_status;


    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public String getAddress_phone() {
        return address_phone;
    }

    public String getAddress_name() {
        return address_name;
    }

    public void setAddress_name(String address_name) {
        this.address_name = address_name;
    }

    public void setAddress_phone(String address_phone) {
        this.address_phone = address_phone;
    }

    public String getAddress_local() {
        return address_local;
    }

    public void setAddress_local(String address_local) {
        this.address_local = address_local;
    }

    public int getAddress_user_id() {
        return address_user_id;
    }

    public void setAddress_user_id(int address_user_id) {
        this.address_user_id = address_user_id;
    }

    public int getAddress_status() {
        return address_status;
    }

    public void setAddress_status(int address_status) {
        this.address_status = address_status;
    }
}
