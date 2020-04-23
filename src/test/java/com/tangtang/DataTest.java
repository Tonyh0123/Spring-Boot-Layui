package com.tangtang;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.zip.GZIPInputStream;

public class DataTest {

//    private static final int numOfEncAndDec = 0x99; //加密解密秘钥
//    private static int dataOfFile = 0; //文件字节内容
//
    public static void main(String[] args) {
        String u = "123456";
        String i = "789";
        System.out.println(u+i);
    }
//
//    private static void EncFile(File srcFile, File encFile) throws Exception {
//        if (!srcFile.exists()) {
//            System.out.println("source file not exixt");
//            return;
//        }
//
//        if (!encFile.exists()) {
//            System.out.println("encrypt file created");
//            encFile.createNewFile();
//        }
//        InputStream fis = new FileInputStream(srcFile);
//        OutputStream fos = new FileOutputStream(encFile);
//
//        while ((dataOfFile = fis.read()) > -1) {
//            fos.write(dataOfFile ^ numOfEncAndDec);
//        }
//        fis.close();
//        fos.flush();
//        fos.close();
//    }
//
//    private static void DecFile(File encFile, File decFile) throws Exception {
//         if (!encFile.exists()) {
//             System.out.println("encrypt file not exixt");
//             return;
//             }
//
//         if (!decFile.exists()) {
//             System.out.println("decrypt file created");
//            decFile.createNewFile();
//             }
//
//         InputStream fis = new FileInputStream(encFile);
//         OutputStream fos = new FileOutputStream(decFile);
//
//         while ((dataOfFile = fis.read()) > -1) {
//             fos.write(dataOfFile ^ numOfEncAndDec);
//             }
//
//         fis.close();
//         fos.flush();
//         fos.close();
//    }

}   