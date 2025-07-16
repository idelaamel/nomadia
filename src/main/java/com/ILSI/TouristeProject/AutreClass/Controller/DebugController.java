package com.ILSI.TouristeProject.AutreClass.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DebugController {

    @GetMapping("/debug-env")
    public String debugEnvironment() {
        StringBuilder result = new StringBuilder();
        result.append("Environment Debug Info:<br><br>");
        
        // Check critical environment variables
        String databaseUrl = System.getenv("DATABASE_URL");
        String springProfile = System.getenv("SPRING_PROFILES_ACTIVE");
        String port = System.getenv("PORT");
        
        result.append("DATABASE_URL: ").append(databaseUrl != null ? "SET" : "NOT SET").append("<br>");
        result.append("SPRING_PROFILES_ACTIVE: ").append(springProfile != null ? springProfile : "NOT SET").append("<br>");
        result.append("PORT: ").append(port != null ? port : "NOT SET").append("<br><br>");
        
        // Check system properties
        String activeProfiles = System.getProperty("spring.profiles.active");
        result.append("System spring.profiles.active: ").append(activeProfiles != null ? activeProfiles : "NOT SET").append("<br>");
        
        return result.toString();
    }
}
