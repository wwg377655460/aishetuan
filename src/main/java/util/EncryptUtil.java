package util;

import org.apache.log4j.Logger;
import test.Encrypt;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.math.BigInteger;
import java.security.interfaces.RSAPrivateKey;

/**
 * Created by wsdevotion on 16/1/23.
 */
public class EncryptUtil {

    private static Logger logger = Logger.getLogger(EncryptUtil.class);

    public static String decrypt(String ctext) throws IOException, ClassNotFoundException {

        BigInteger c = new BigInteger(ctext);
        // 读取私钥
        FileInputStream f = new FileInputStream("privatekey.dat");
        ObjectInputStream b = new ObjectInputStream(f);
        RSAPrivateKey prk = (RSAPrivateKey) b.readObject();
        BigInteger d = prk.getPrivateExponent();
        // 获取私钥参数及解密
        BigInteger n = prk.getModulus();
        BigInteger m = c.modPow(d, n);
        // 显示解密结果
        byte[] mt = m.toByteArray();
        System.out.println("PlainText is ");
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < mt.length; i++) {
            stringBuffer.append((char) mt[i]);
        }

        String mes = stringBuffer.toString();
        logger.info("PlainText is " + mes);
        return mes;
    }
}
