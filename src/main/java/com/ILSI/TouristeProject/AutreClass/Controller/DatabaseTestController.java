package com.ILSI.TouristeProject.AutreClass.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;

@RestController
public class DatabaseTestController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/test-db")
    public String testDatabase() {
        try {
            Connection connection = dataSource.getConnection();
            String url = connection.getMetaData().getURL();
            String user = connection.getMetaData().getUserName();
            connection.close();
            
            return "Database connection successful!<br>" +
                   "URL: " + url + "<br>" +
                   "User: " + user + "<br>" +
                   "Driver: " + connection.getMetaData().getDriverName();
        } catch (Exception e) {
            return "Database connection failed: " + e.getMessage();
        }
    }
}
