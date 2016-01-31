package util;

/**
 * Created by wsdevotion on 15/11/16.
 */
public class StrUtils {

    /**
     * 字符串为 null 或者为  "" 时返回 true
     */
    public static boolean isBlank(String str) {
        return str == null || "".equals(str.trim());
    }

}
