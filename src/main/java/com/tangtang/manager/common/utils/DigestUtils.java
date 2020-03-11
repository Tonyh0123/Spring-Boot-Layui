package com.tangtang.manager.common.utils;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;

/**
 * @Title: DigestUtils
 * @Description:
 * @author: tangtang
 * @version: 1.0
 * @date: 2020/3/5 15:09
 */
public class DigestUtils {

    /**
     *
     * 功能描述: MD5加密账号密码
     *
     * @param: 
     * @return: 
     * @auther: tangtang
     * @date: 2020/3/5 15:11
     */
    public static String Md5(String userName,String password){
        Md5Hash hash = new Md5Hash(password, ByteSource.Util.bytes(userName), 2);
        return hash.toString();
    }
}
