package com.guozhaotong.shoppingsystem.util;

import org.springframework.util.Base64Utils;
import org.springframework.util.DigestUtils;

/**
 * @author 郭朝彤
 * @date 2019/3/3.
 */
public class Encryption {

    /**
     * md5加密
     * @param text
     * @return
     */
    public static String md5(String text){
        return DigestUtils.md5DigestAsHex(text.getBytes());
    }

    public static String base64(String text){
        return Base64Utils.encodeToString(text.getBytes());
    }

    public static String deBase64(String text){
        byte[] bytes = Base64Utils.decodeFromString(text);
        String res = new String(bytes);
        return res;
    }

    public static void main(String[] args) {
        String s = "text";
        System.out.println(deBase64(base64(s)).equals(s));
    }
}
