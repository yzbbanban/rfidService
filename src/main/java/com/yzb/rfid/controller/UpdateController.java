package com.yzb.rfid.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.yzb.rfid.service.RfidUpdateService;
import com.yzb.rfid.util.ResultCode;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Controller
public class UpdateController {

    private Logger log = LoggerFactory.getLogger(UpdateController.class);

    @Resource
    RfidUpdateService service;

    // 更新
    @RequestMapping(value = "/rfidUpdate/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResultCode<String> update(String version) {
        String msg = service.getRfidUpdateMsgTask(version);
        return new ResultCode(msg, msg);
    }

    // 写入更新
    @RequestMapping(value = "/rfidUpdate/addUpdate.do", method = RequestMethod.POST)
    @ResponseBody
    public ResultCode<String> addUpdate(String version) {
        System.out.println(version);
        String msg = service.setRfidUpdateMsgTask(version);
        return new ResultCode(msg, msg);
    }

    @PostMapping("/uploadApk")
    public String upload(@RequestParam MultipartFile file) throws IllegalStateException, IOException {
        // 判断是否为空文件
        if (file.isEmpty()) {
            return "上传文件不能为空";
        }
        // 文件类型
        String contentType = file.getContentType();
        // springmvc处理后的文件名
        String fileName = file.getName();
        log.info("服务器文件名：" + fileName);
        // 原文件名即上传的文件名
        String origFileName = file.getOriginalFilename();
        // 文件大小
        Long fileSize = file.getSize();

        // 保存文件
        // 可以使用二进制流直接保存
        // 这里直接使用transferTo

        file.transferTo(new File(System.getProperty("user.dir") + "/" + origFileName));

        return "上传成功";

    }

    @GetMapping("/CX.apk")
    public String downloadFile(HttpServletRequest request, HttpServletResponse response) {
        String fileName = "/CX.apk";// 文件名
        if (fileName != null) {
            //设置文件路径
            File file = new File(System.getProperty("user.dir") + "/CX.apk");
            //File file = new File(realPath , fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    return "下载成功";
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return "下载失败";
    }


}
