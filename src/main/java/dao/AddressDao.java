package dao;

import entry.Address;
import org.nutz.dao.Cnd;
import util.DbFactory;

import java.util.List;

/**
 * Created by wsdevotion on 16/2/1.
 */
public class AddressDao extends DbFactory<Address> {

    @Override
    public Address get(int id) {
        return getDao().fetch(Address.class, id);
    }

    //通过user_id获取地址信息
    public List<Address> getAddressesByUserId(int user_id) {
        return getDao().query(Address.class, Cnd.wrap("address_user_id = " + user_id));
    }

    //添加地址信息
    public void insertAddress(Address address) {
        getDao().insert(address);
    }

}
