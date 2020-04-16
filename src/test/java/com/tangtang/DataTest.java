package com.tangtang;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.zip.GZIPInputStream;

public class DataTest {

    /**
     * Uncompress the incoming file.  
     * @param inFileName Name of the file to be uncompressed  
     */
    private static void doUncompressFile(String inFileName) {

        try {

            if (!getExtension(inFileName).equalsIgnoreCase("gz")) {
                System.err.println("File name must have extension of \".gz\"");
                System.exit(1);
            }

            System.out.println("Opening the compressed file.");
            GZIPInputStream in = null;
            try {
                in = new GZIPInputStream(new FileInputStream(inFileName));
            } catch(FileNotFoundException e) {
                System.err.println("File not found. " + inFileName);
                System.exit(1);
            }

            System.out.println("Open the output file.");
            String outFileName = getFileName(inFileName);
            FileOutputStream out = null;
            try {
                out = new FileOutputStream(outFileName);
            } catch (FileNotFoundException e) {
                System.err.println("Could not write to file. " + outFileName);
                System.exit(1);
            }

            System.out.println("Transfering bytes from compressed file to the output file.");
            byte[] buf = new byte[1024];
            int len;
            while((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }

            System.out.println("Closing the file and stream");
            in.close();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Used to extract and return the extension of a given file.  
     * @param f Incoming file to get the extension of  
     * @return <code>String</code> representing the extension of the incoming  
     *         file.  
     */
    public static String getExtension(String f) {
        String ext = "";
        int i = f.lastIndexOf('.');

        if (i > 0 &&  i < f.length() - 1) {
            ext = f.substring(i+1);
        }
        return ext;
    }

    /**
     * Used to extract the filename without its extension.  
     * @param f Incoming file to get the filename  
     * @return <code>String</code> representing the filename without its  
     *         extension.  
     */
    public static String getFileName(String f) {
        String fname = "";
        int i = f.lastIndexOf('.');

        if (i > 0 &&  i < f.length() - 1) {
            fname = f.substring(0,i);
        }
        return fname;
    }

    public static void getAllFileName(String path,ArrayList<String> listFileName){
        File file = new File(path);
        File [] files = file.listFiles();
        String [] names = file.list();
        if(names != null){
            String [] completNames = new String[names.length];
            for(int i=0;i<names.length;i++){
                completNames[i]=path+names[i];
            }
            listFileName.addAll(Arrays.asList(completNames));
        }
    }

    public static void readFile(String m,List<String> list) {
        String pathname = m;
        File file=new File(pathname);
        BufferedReader br=null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"utf-8"));
            String line;
            while ((line = br.readLine()) != null) {
                // 一次读入一行数据
                list.add(line);
                System.out.println(line);
            }

//            Iterator<String> iterator = list.iterator();
//            while (iterator.hasNext()) {
//                System.out.println(iterator.next());
//            }
            System.out.println("listtttttttttttttttt:" + list.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sole entry point to the class and application.  
     * @param args Array of String arguments.  
     */
    public static void main(String[] args) {

        ArrayList<String> listFileName = new ArrayList<String>();
        getAllFileName("C:\\Users\\Adminstrador\\logs\\",listFileName);
        List<String> list = new ArrayList<>();
        for(String name:listFileName){
            if(name.endsWith(".0")){
//                doUncompressFile(name);
                readFile(name,list);
            }else if (name.endsWith(".log")){
                readFile(name,list);
            }
        }


    }

}   