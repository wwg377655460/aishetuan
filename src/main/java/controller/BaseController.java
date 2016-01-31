package controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import dao.UserDao;
import exception.AuthException;
import exception.NullRequestException;
import org.nutz.json.Json;
import redis.clients.jedis.Jedis;
import spark.Request;
import spark.servlet.SparkApplication;
import util.EncryptUtil;
import util.RedisFactory;
import util.StrUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by wsdevotion on 16/1/22.
 */
public class BaseController {



    public static String getStrOrDie(JSONObject jsonObject, String key) throws NullRequestException {
        if (jsonObject.containsKey(key)) {
            String value = jsonObject.getString(key);
            if (StrUtils.isBlank(value)) {
                throw new NullRequestException("param " + key + " is empty");
            }
            return value;
        } else {
            throw new NullRequestException("param " + key + " is null");
        }
    }



    public static int getIntOrDie(JSONObject jsonObject, String key) throws NullRequestException {
        try {
            return Integer.valueOf(jsonObject.getString(key));
        } catch (Exception e) {
            throw new NullRequestException("param " + key + " is empty");
        }
    }


    /**
     * 判断body内的值是否为空，解析成json格式
     * @param request
     * @return
     * @throws NullRequestException
     */
    public static JSONObject parseJson(Request request) throws NullRequestException {
        String body = request.body();
        if (StrUtils.isBlank(body)) {
            throw new NullRequestException();
        }

        JSONObject jsonObject = JSON.parseObject(body);
        return jsonObject;
    }



    /**
     * 验证用户身份信息，客户端传输格式 传输时间的毫秒数|登录时返回的验证信息
     * @param request
     * @return
     * @throws Exception
     */
    public static boolean authUserMessage(Request request, String user_phone) throws IOException, ClassNotFoundException, AuthException {
        String access_token = request.headers("access_token");
//        access_token = EncryptUtil.decrypt(access_token);
//        String [] mes = access_token.split("[|]");
        //判断信息是否过时
//        long time = System.currentTimeMillis();
        //小于5分钟说明信息正确
//        if(time - Long.parseLong(mes[0]) > 5 * 60 * 1000){
//            throw new AuthException("信息过时");
//        }
        Jedis jedis = RedisFactory.get();
        if (jedis.exists(access_token)) {
            if (jedis.get(access_token).equals(user_phone)) {
                jedis.close();
                return true;
            }
        }
        if (jedis != null) {
            jedis.close();
        }

        return false;
    }



    public static String returnStatusMes(String mes){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", mes);
        return jsonObject.toString();
    }

    public static String returnMes(int status, String ... args){
        JSONObject jsonObject = new JSONObject();
        if(args.length % 2 != 0){
            return null;
        }
        String key = "";
        for(int i=0; i<args.length; i++){
            if(i % 2 == 0)
                key = args[i];
            else
                jsonObject.put(key, args[i]);
        }
        jsonObject.put("status", status);

        return jsonObject.toString();
    }

    public static String parseJsonObjectToString(JSONObject jsonObject) {
        return jsonObject.toJSONString().replace("\\", "");
    }




}
