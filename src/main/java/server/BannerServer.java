package server;


import entry.Banner;

import java.util.List;

/**
 * Created by wsdevotion on 16/1/30.
 */
public class BannerServer extends MainServer {

    /**
     * 获取所有轮播图片信息
     * @return
     */
    public List<Banner> getAll(){
        return getBannerDao().getAll();
    }
}
