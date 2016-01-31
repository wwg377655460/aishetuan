package util;


import config.ConstMes;
import config.GetProMessage;
import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;

/**
 * Created by wsdevotion on 16/1/22.
 */
public class RedisUtil {

    public static void initRedis() {
        //Redis初始化
        RedisFactory.init(GetProMessage.getProMessage("host", ConstMes.getREDISPROMES()),
        Integer.parseInt(GetProMessage.getProMessage("port", ConstMes.getREDISPROMES())));

        //清空redis
        Jedis jedis = null;
        try {
            jedis = RedisFactory.get();
            jedis.flushAll();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
