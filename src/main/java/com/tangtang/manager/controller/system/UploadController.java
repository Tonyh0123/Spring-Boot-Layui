package com.tangtang.manager.controller.system;

import com.alibaba.fastjson.JSON;
import com.tangtang.manager.pojo.BaseSuccessfulCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("upload")
@MultipartConfig
public class UploadController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/imagesUpload", method = RequestMethod.POST)
    @ResponseBody
    public String imagesUpload(@RequestParam(value="file") MultipartFile file,
                                           HttpServletRequest request, HttpServletResponse response)throws Exception {
        Map<String,Object> data = new HashMap();
        if (file!=null){
            logger.info("【图片上传】-->开始上传文件" + String.format(new Date().toString(), "yy-mm-dd hh:mm:ss"));
            String path = "F:/caseImages";
            String[] fileNameArray = file.getOriginalFilename().split("\\.");
            String fileName = fileNameArray[1];
            String newFileName = new Date().toString().replace(" ","-").replace(":","-") + UUID.randomUUID().toString().replace("-","") + "." + fileName;
            System.out.println(newFileName);
            String url = path + "/" +  newFileName;
            InputStream inputStream = file.getInputStream();
            pubUpload(inputStream,url);
            data.put("code",0);
            data.put("fileUrl","/image/"+newFileName);
            logger.info("【图片上传】-->上传文件完毕" + String.format(new Date().toString(), "yy-mm-dd hh:mm:ss"));
            }
        String res = JSON.toJSONString(data);

        return res;
    }

    @RequestMapping(value = "/noticeFileUpload", method = RequestMethod.POST)
    @ResponseBody
    public String noticeFileUpload(@RequestParam(value="file") MultipartFile file,
                               HttpServletRequest request, HttpServletResponse response)throws Exception {
        Map<String,Object> data = new HashMap();
        if (file!=null){
            logger.info("【公告文件上传】-->开始上传文件" + String.format(new Date().toString(), "yy-mm-dd hh:mm:ss"));
            String path = "F:/noticeFile";
            String[] fileNameArray = file.getOriginalFilename().split("\\.");
            String webShowName = fileNameArray[0];
            String fileSuffix = fileNameArray[1];
            SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS" );
            SimpleDateFormat sdf1 =new SimpleDateFormat("yyyy-MM-dd" );
            String date = sdf.format(new Date()).replace(" ","-").replace(":","-");
            String fileDate = sdf1.format(new Date()).replace(" ","-").replace(":","-");
            String uuid = UUID.randomUUID().toString().replace("-","");
            String newFileName = webShowName + "_" + date + "_" + uuid + "." + fileSuffix;
            System.out.println(newFileName);
            String url = path + "/" +  newFileName;
            InputStream inputStream = file.getInputStream();

            pubUpload(inputStream,url);


            data.put("code",0);
            data.put("fileName",webShowName);
            data.put("fileDate",fileDate);
            data.put("fileUrl","/noticeFile/"+newFileName);
            logger.info("【公告文件上传】-->上传文件完毕" + String.format(new Date().toString(), "yy-mm-dd hh:mm:ss"));
        }
        String res = JSON.toJSONString(data);

//
        return res;
    }



    @RequestMapping(value = "/identifyConfirmFileUpload", method = RequestMethod.POST)
    @ResponseBody
    public String identifyConfirmFileUpload(@RequestParam(value="file") MultipartFile file,
                                   HttpServletRequest request, HttpServletResponse response)throws Exception {
        Map<String,Object> data = new HashMap();
        if (file!=null){
            logger.info("【身份证明文件上传】-->开始上传文件" + String.format(new Date().toString(), "yy-mm-dd hh:mm:ss"));
            String path = "F:/identifyConfirmFile";
            String[] fileNameArray = file.getOriginalFilename().split("\\.");
            String webShowName = fileNameArray[0];
            String fileSuffix = fileNameArray[1];
            SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS" );
            SimpleDateFormat sdf1 =new SimpleDateFormat("yyyy-MM-dd" );
            String date = sdf.format(new Date()).replace(" ","-").replace(":","-");
            String fileDate = sdf1.format(new Date()).replace(" ","-").replace(":","-");
            String uuid = UUID.randomUUID().toString().replace("-","");
            String newFileName = webShowName + "_" + date + "_" + uuid + "." + fileSuffix;
            System.out.println(newFileName);
            String url = path + "/" +  newFileName;
            InputStream inputStream = file.getInputStream();

            pubUpload(inputStream,url);


            data.put("code",0);
            data.put("fileName",webShowName);
            data.put("fileDate",fileDate);
            data.put("fileUrl","/identifyConfirmFile/"+newFileName);
            logger.info("【身份证明文件上传】-->上传文件完毕" + String.format(new Date().toString(), "yy-mm-dd hh:mm:ss"));
        }
        String res = JSON.toJSONString(data);
        return res;
    }


    /**
     * 抽取公共上传模块
     * @param inputStream
     * @param url
     * @throws IOException
     */
    public void pubUpload(InputStream inputStream, String url) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(url);
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(buffer)) > 0) {
            // 使用FileOutputStream输出流将缓冲区的数据写入到指定的目录当中
            fileOutputStream.write(buffer, 0, len);
        }
        fileOutputStream.flush();
        fileOutputStream.close();
        inputStream.close();
    }
}
