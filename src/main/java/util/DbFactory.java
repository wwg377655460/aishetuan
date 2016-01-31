package util;

import org.nutz.dao.Dao;

/**
 * Created by wsdevotion on 16/1/24.
 */
public abstract class DbFactory<T> {


    private Dao dao;

    public abstract T get(int id);

    public Dao getDao() {
        if (this.dao == null) {
            this.dao = DbUtils.getNutzDao();
        }
        return this.dao;
    }

}
