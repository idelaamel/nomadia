package com.ILSI.TouristeProject.AutreClass.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/")
    @ResponseBody
    public String home() {
        return "TouristeProject API is running successfully! " +
               "<br><br>Available endpoints:" +
               "<br>• <a href='/swagger-ui.html'>API Documentation</a>" +
               "<br>• <a href='/api/public'>Public API endpoints</a>" +
               "<br>• <a href='/actuator/health'>Health Check</a>";
    }

    @GetMapping("/health")
    @ResponseBody
    public String health() {
        return "OK";
    }
}
