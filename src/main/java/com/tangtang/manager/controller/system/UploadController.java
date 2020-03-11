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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
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
            FileOutputStream fileOutputStream = new FileOutputStream(url);
            while( inputStream.available()!= 0){
                fileOutputStream.write(inputStream.read());
            }

            fileOutputStream.close();
            inputStream.close();
            data.put("code",0);
            data.put("fileUrl","/image/"+newFileName);
            logger.info("【图片上传】-->上传文件完毕" + String.format(new Date().toString(), "yy-mm-dd hh:mm:ss"));
//            InputStream in = file.getInputStream();
//            StringBuffer sb = new StringBuffer();
//            byte[] tempbytes = new byte[1024];
//            int byteread = 0;
//            while ((byteread = in.read(tempbytes)) != -1) {
//                String str = new String(tempbytes, 0, byteread);
//                sb.append(str);
//            }
//
//            String path = "D:/file/";
//            String fileName = "test.jpg";
//            if(null != sb){
//                try {
//                    File image = new File(path+fileName);//文件路径（路径+文件名）
//                    if (!image.exists()) {   //文件不存在则创建文件，先创建目录
//                        File dir = new File(path);
//                        dir.mkdirs();
//                        image.createNewFile();
//                    }
//                    FileOutputStream outStream = new FileOutputStream(image); //文件输出流将数据写入文件
//                    outStream.write();
//                    outStream.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    // do something
//                } finally {
//                    // do something
//                }
            }
        String res = JSON.toJSONString(data);

//
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
            FileOutputStream fileOutputStream = new FileOutputStream(url);
            while( inputStream.available()!= 0){
                fileOutputStream.write(inputStream.read());
            }

            fileOutputStream.close();
            inputStream.close();
            data.put("code",0);
            data.put("fileName",webShowName);
            data.put("fileDate",fileDate);
            data.put("fileUrl","/noticeFile/"+newFileName);
            logger.info("【图片上传】-->上传文件完毕" + String.format(new Date().toString(), "yy-mm-dd hh:mm:ss"));
        }
        String res = JSON.toJSONString(data);

//
        return res;
    }
}
