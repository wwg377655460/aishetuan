package dao;

import entry.Banner;
import util.DbFactory;

import java.util.List;

/**
 * Created by wsdevotion on 16/1/30.
 */
public class BannerDao extends DbFactory<Banner> {


    @Override
    public Banner get(int id) {
        return getDao().fetch(Banner.class, id);
    }

    public List<Banner> getAll(){
        return getDao().query(Banner.class, null);
    }
}
