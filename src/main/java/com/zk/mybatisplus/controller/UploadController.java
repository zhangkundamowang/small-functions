package com.zk.mybatisplus.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 单/多文件上传
 */
@Controller
public class UploadController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);

    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }
        //获取文件名称
        String fileName = file.getOriginalFilename();
        //获取当前项目的实际路径+自定义路径file
        String filePath = System.getProperty("user.dir") + "\\file\\";
        //如果文件夹不存在则创建文件夹
        File f = new File(filePath);
        if (!f.exists()) {
            f.mkdir();
        }
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            LOGGER.info("上传成功");
            return "上传成功";
        } catch (IOException e) {
            LOGGER.error(e.toString(), e);
        }
        return "上传失败！";
    }

    /**
     * 多文件上传
     */
    @PostMapping("/multiUpload")
    @ResponseBody
    public String multiUpload(@RequestParam("file1") MultipartFile file1,
                              @RequestParam("file2") MultipartFile file2,
                              @RequestParam("file3") MultipartFile file3) {
        List<MultipartFile> files = new ArrayList<>();
        files.add(file1);
        files.add(file2);
        files.add(file3);
        //获取当前项目的实际路径+自定义路径file
        String filePath = System.getProperty("user.dir") + "\\file\\";
        //如果文件夹不存在则创建文件夹
        File f = new File(filePath);
        if (!f.exists()) {
            f.mkdir();
        }
        //循环上传文件
        for (int i = 0; i < files.size(); i++) {
            MultipartFile file = files.get(i);
            if (file.isEmpty()) {
                return "上传第" + (i++) + "个文件失败";
            }
            String fileName = file.getOriginalFilename();

            File dest = new File(filePath + fileName);
            try {
                file.transferTo(dest);
                LOGGER.info("第" + (i + 1) + "个文件上传成功");
            } catch (IOException e) {
                LOGGER.error(e.toString(), e);
                return "上传第" + (i++) + "个文件失败";
            }
        }
        return "上传成功";
    }

}

