package config;


import java.io.FileInputStream;
import java.net.URISyntaxException;
import java.util.Properties;

/**
 * Created by wsdevotion on 15/11/19.
 */
public class GetProMessage {

    public static String getProMessage(String name, String proname) {
        Properties pro = new Properties();
        try {
            String path = GetProMessage.class.getClassLoader().getResource("").toURI().getPath();
            pro.load(new FileInputStream(path + "/" + proname));
            return pro.getProperty(name);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

}
