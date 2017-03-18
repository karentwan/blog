package cn.karent.util;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;

/**
 * Created by wan on 2017/1/21.
 * md5加密
 */
public class MD5Util {

    /**
     * 按照md5加密字符串
     * @param str 待加密的字符串
     * @return 返回一个加密加密后的字符串
     */
    public static String encodeMD5(String str) throws Exception{
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String newStr = base64Encoder.encode(md5.digest(str.getBytes("utf-8")));
        return newStr;
    }

}
