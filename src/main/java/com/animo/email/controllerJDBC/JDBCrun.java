package com.animo.email.controllerJDBC;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@SpringBootApplication
@RequestMapping("email")
public class JDBCrun {
    @Autowired
    JDBCservice cservice;

    @RequestMapping("/get")
    List<Map<String, Object>> getUUID(){
        System.out.println("数据库连接成功");
        return cservice.getUUID();
    }


}
