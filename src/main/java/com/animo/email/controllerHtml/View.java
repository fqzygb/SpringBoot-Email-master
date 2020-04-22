package com.animo.email.controllerHtml;

import com.animo.email.Service.*;
import com.animo.email.controllerJDBC.JDBCservice;
import com.animo.email.result.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.TemplateEngine;

import java.time.LocalDateTime;

@Controller
@Configuration //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling // 2.开启定时任务
@RequestMapping("email")

public class View {

    @Autowired
    JDBCservice cservice;

    @Autowired
    private IMailService mailService;//注入发送邮件的各种实现方法
    @Autowired
    private TemplateEngine templateEngine;//注入模板引擎
    /**
     * 发送普通邮件
     * @return
     */


    /*
    定时执行查询数据库是否存在超时单，如果存在则发送邮件提醒
     */
    @RequestMapping("/fasong")
    @Scheduled(cron = "0 0 9,11,15,18 * * ?")// 每天的9点、11点、15点、18点都执行一次
    public String fasong(){
        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
        if (cservice.getUUID().size()!= 0) {


            try {
                mailService.sendSimpleMail("544724131@qq.com", "严重超时单", "存在严重超时单，请及时处理");
                mailService.sendSimpleMail("809369405@qq.com","严重超时单","存在严重超时单，请及时处理");
                System.out.println("发送成功");

            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("发送失败");

            }
        }
        return "index";
    }




    /*
   首页
    */

    @RequestMapping("/index")
    public String index(){
        //存在超时单，跳到发邮件
        if (cservice.getUUID().size()!= 0){
            return "redirect:/email/fasong";
        }

        //没有超时单
        return "redirect:/index_1";

    }


    //测试没有超时单的情况
    @RequestMapping("/a")
    public String a(){
        System.out.println("执行了/a");
        return "index_1";
    }
}
