package com.tangtang.manager.common.utils;

import java.io.*;

public class Enc_DecTools {
    private static final int numOfEncAndDec = 0x99; //加密解密秘钥
    private static int dataOfFile = 0; //文件字节内容
    public static void EncFile(File srcFile, File encFile) throws Exception {
        if (!srcFile.exists()) {
            System.out.println("source file not exixt");
            return;
        }

        if (!encFile.exists()) {
            System.out.println("encrypt file created");
            encFile.createNewFile();
        }
        InputStream fis = new FileInputStream(srcFile);
        OutputStream fos = new FileOutputStream(encFile);

        while ((dataOfFile = fis.read()) > -1) {
            fos.write(dataOfFile ^ numOfEncAndDec);
        }
        fis.close();
        fos.flush();
        fos.close();
    }

    public static void DecFile(File encFile, File decFile) throws Exception {
        if (!encFile.exists()) {
            return;
        }

        if (!decFile.exists()) {
            decFile.createNewFile();
        }

        InputStream fis = new FileInputStream(encFile);
        OutputStream fos = new FileOutputStream(decFile);

        while ((dataOfFile = fis.read()) > -1) {
            fos.write(dataOfFile ^ numOfEncAndDec);
        }

        fis.close();
        fos.flush();
        fos.close();
    }
}
