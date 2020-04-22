package com.animo.email.controllerJDBC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class JDBCserviceImpl implements JDBCservice {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> getUUID(){
        System.out.println("----------------------------------------------------");
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from fqz_view_yanzhongchaoshidan");
        for(Map<String, Object> mapl:maps){
            Set<Map.Entry<String, Object>> entries = mapl.entrySet();
            for(Map.Entry<String, Object> entry : entries){
                System.out.println(entry.getKey() + "=" + entry.getValue());
            }
        }
        return maps;
    }
}
