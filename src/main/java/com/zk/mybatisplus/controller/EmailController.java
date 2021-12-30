package com.zk.mybatisplus.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "邮件管理", tags = "邮件管理")
public class EmailController {

    @Autowired
    private JavaMailSender mailSender;

    @ApiOperation(value = "测试发邮件")
    @GetMapping("send")
    private void send(){
        SimpleMailMessage message = new SimpleMailMessage();
        // 发件人
        message.setFrom("luckkun123@163.com");
        // 收件人
        message.setTo("1689418664@qq.com");
        // 邮件标题
        message.setSubject("Java发送邮件测试");
        // 邮件内容
        message.setText("您好，这是一条用于测试SpringBoot邮件发送功能的邮件！");
        // 抄送人
        //message.setCc("xxx@qq.com");
        mailSender.send(message);
    }
}
