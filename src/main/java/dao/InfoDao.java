package dao;

import entry.Info;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import util.DbFactory;

import java.util.List;

/**
 * Created by wsdevotion on 16/2/1.
 */
public class InfoDao extends DbFactory<Info> {

    @Override
    public Info get(int id) {
        return getDao().fetch(Info.class, id);
    }

    //添加信息
    public void insert(Info info) {
        getDao().insert(info);
    }

    //通过user_id获取用户信息
    public List<Info> getInfoByUserId(int user_id) {
        return getDao().query(Info.class, Cnd.where("info_user_id", "=", + user_id ).and("info_status", "=", 1));
    }

    //将info标示为已读
    public void updateInfoStatus(int user_id) {
        getDao().update(Info.class, Chain.make("info_status", 0), Cnd.where("info_user_id", "=", user_id));
    }
}
