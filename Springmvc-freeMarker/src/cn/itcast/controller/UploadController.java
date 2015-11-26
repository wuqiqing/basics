package cn.itcast.controller;

import cn.itcast.utils.SSMConstants;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Controller
@RequestMapping("/upload")
public class UploadController {


    @RequestMapping("uploadPic")
    public void uploadPic(HttpServletRequest request, HttpServletResponse response, String fileName, PrintWriter out) {
        //把Request对象转换成多部件上传类型
        MultipartHttpServletRequest mh = (MultipartHttpServletRequest) request;
        CommonsMultipartFile cm = (CommonsMultipartFile) mh.getFile(fileName);

        byte[] fbytes = cm.getBytes();

        String newFileName = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        newFileName = sdf.format(new Date());

        Random randon = new Random();
        for (int i = 0; i < 3; i++) {

            newFileName = newFileName + randon.nextInt(10);
        }

        String originalFilename = cm.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        //创建一个Jersey客户端
        Client client = Client.create();

        //绑定上传路径到客户端
        WebResource resource = client.resource(SSMConstants.PIC_HOST + "/upload/" + newFileName + suffix);

        resource.put(String.class, fbytes);

        String fullPath = SSMConstants.PIC_HOST + "/upload/" + newFileName + suffix;

        String relativePath = "/upload/" + newFileName + suffix;

        String result = "{\"fullPath\":\"" + fullPath + "\",\"ralativePath\":\"" + relativePath + "\"}";
        out.print(result);
    }

}
