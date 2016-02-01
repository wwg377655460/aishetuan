package dao;

import entry.Order;
import util.DbFactory;

/**
 * Created by wsdevotion on 16/2/1.
 */
public class OrderDao extends DbFactory<Order> {

    @Override
    public Order get(int id) {
        return getDao().fetch(Order.class, id);
    }


}
