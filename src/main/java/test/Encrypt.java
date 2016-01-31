package test;

import java.io.*;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * Created by wsdevotion on 16/1/23.
 */
public class Encrypt {


    private static void generateKey() {
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(1024);
            KeyPair kp = kpg.genKeyPair();
            PublicKey pbkey = kp.getPublic();
            PrivateKey prkey = kp.getPrivate();
            // 保存公钥
            FileOutputStream f1 = new FileOutputStream("pubkey.dat");
            ObjectOutputStream b1 = new ObjectOutputStream(f1);
            b1.writeObject(pbkey);
            // 保存私钥
            FileOutputStream f2 = new FileOutputStream("privatekey.dat");
            ObjectOutputStream b2 = new ObjectOutputStream(f2);
            b2.writeObject(prkey);
        } catch (Exception e) {
        }
    }

    private static String encrypt(String s) throws Exception {
        // 获取公钥及参数e,n
        FileInputStream f = new FileInputStream("pubkey.dat");
        ObjectInputStream b = new ObjectInputStream(f);
        RSAPublicKey pbk = (RSAPublicKey) b.readObject();
        BigInteger e = pbk.getPublicExponent();
        BigInteger n = pbk.getModulus();
//        System.out.println("e= " + e);
//        System.out.println("n= " + n);
        // 获取明文m
        byte ptext[] = s.getBytes("UTF-8");
        BigInteger m = new BigInteger(ptext);
        // 计算密文c
        BigInteger c = m.modPow(e, n);
//        System.out.println("c= " + c);

        return c.toString();
    }

    public static void decrypt(String ctext) throws Exception {

        BigInteger c = new BigInteger(ctext);
        // 读取私钥
        FileInputStream f = new FileInputStream("privatekey.dat");
        ObjectInputStream b = new ObjectInputStream(f);
        RSAPrivateKey prk = (RSAPrivateKey) b.readObject();
        BigInteger d = prk.getPrivateExponent();
        // 获取私钥参数及解密
        BigInteger n = prk.getModulus();
//        System.out.println("d= " + d);
//        System.out.println("n= " + n);
        BigInteger m = c.modPow(d, n);
        // 显示解密结果
        System.out.println("m= " + m);
        byte[] mt = m.toByteArray();
        System.out.println("PlainText is ");
        for (int i = 0; i < mt.length; i++) {
            System.out.print((char) mt[i]);
        }
    }

    public static void main(String args[]) {
        try {
            String s1 = "hello123";
            String s = encrypt(s1);
            System.out.println(s + "-------------------");
            decrypt(s);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

}
