package util;

import config.JdbcConnection;
import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;

/**
 * Created by wsdevotion on 16/1/24.
 */
public class DbUtils {

    private static Dao nutzDao;

    public static Dao getNutzDao() {
        if (nutzDao == null) {
            nutzDao = new NutDao(JdbcConnection.getDataSource());
        }

        return nutzDao;
    }
}
