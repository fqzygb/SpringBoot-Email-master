package com.animo.email.controller;

import com.animo.email.Service.*;
import com.animo.email.Service.impl.IMailServiceImpl;
import com.animo.email.result.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;



@RestController
@RequestMapping("email")
public class EmailController {

    @Autowired
    private IMailService mailService;//注入发送邮件的各种实现方法
    @Autowired
    private TemplateEngine templateEngine;//注入模板引擎


    /**
     * 发送普通邮件
     * @return
     */
    @RequestMapping("/sendSimpleMail")
    public ServerResponse sendSimpleMail(){
        try {
            mailService.sendSimpleMail("544724131@qq.com","SpringBoot Email","这是一封普通的SpringBoot测试邮件");


        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("发送失败");
            return ServerResponse.createByError(-1,"邮件发送失败!!");
        }
        System.out.println("发送成功");
        return ServerResponse.createBySuccess(0,"发送成功");
    }

    /**
     * 发送HTMl邮件
     * @return
     */
    @RequestMapping("/htmlEmail")
    public ServerResponse htmlEmail(){
        try {
            mailService.sendHtmlMail("544724131@qq.com","SpringBoot Email","<body style=\"text-align: center;margin-left: auto;margin-right: auto;\">\n"
                    + " <div id=\"welcome\" style=\"text-align: center;position: absolute;\" >\n"
                    +"      <h3>欢迎</h3>\n"
                    +"      <span>https://www.baidu.com/</span>"
                    + "     <div\n"
                    + "         style=\"text-align: center; padding: 10px\"><a style=\"text-decoration: none;\" href=\"https://www.baidu.com/\" target=\"_bank\" ><strong>欢迎:)</strong></a></div>\n"
            + "     <div\n" + "         style=\"text-align: center; padding: 4px\">谢谢</div>\n"
                    + "     <img width=\"180px\" height=\"180px\"\n"
                    + "         src=\"https://pics3.baidu.com/feed/ca1349540923dd545623a95e0dd038d89d824897.jpeg?token=0ceaa1552be3ed850e20b1b919b8e7e0\">\n"
                    + " </div>\n" + "</body>");
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("发送失败");
            return ServerResponse.createByError(-1,"邮件发送失败!!");
        }
        System.out.println("发送成功");
        return ServerResponse.createBySuccess();
    }

    /**
     * 发送附件
     * @return
     */
    @RequestMapping("/attachmentsMail")
    public ServerResponse attachmentsMail(){
        try {
            String filePath = "C:\\ReadMe.txt";
            mailService.sendAttachmentsMail("544724131@qq.com", "这是一封带附件的邮件", "邮件中有附件，请注意查收！", filePath);
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("发送失败");
            return ServerResponse.createByError(-1,"邮件发送失败!!");
        }
        System.out.println("发送成功");
        return ServerResponse.createBySuccess();
    }

    /**
     * 发送静态资源
     * @return
     */
    @RequestMapping("/resourceMail")
    public ServerResponse resourceMail(){
        try {
            String rscId = "IJPay";
            String content = "<html><body>这是有图片的邮件<br/><img src=\'cid:" + rscId + "\' ></body></html>";
            String imgPath = "D:\\BaiduNetdiskDownload\\[极品网红资源合集] (1)\\极品网红资源合集 (1)\\园田海未旗袍\\15 (1).jpg";
            mailService.sendResourceMail("544724131@qq.com", "这邮件中含有图片", content, imgPath, rscId);

        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("发送失败");
            return ServerResponse.createByError(-1,"邮件发送失败!!");
        }
        System.out.println("发送成功");
        return ServerResponse.createBySuccess("发送成功");

    }

    @RequestMapping("/templateMail")
    public ServerResponse templateMail(){
        try {
            Context context = new Context();
            context.setVariable("fengqz9", "fengqz9");
            context.setVariable("author", "fengqz9");
            context.setVariable("url", "https://www.baidu.com/");
            String emailContent = templateEngine.process("sendMailModel", context);
            mailService.sendHtmlMail("544724131@qq.com", "这是模板邮件", emailContent);
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("发送失败");
            return ServerResponse.createByError(-1,"邮件发送失败!!");
        }
        System.out.println("发送成功");
        return ServerResponse.createBySuccess();
    }
}
