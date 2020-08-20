package cn.lch.java_spring_boot.modules.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/common")
public class CommonController {

    /**
     * http://localhost/common/dashboard ---- get
     */
    @GetMapping("/dashboard")
    public String dashboardPage() {
        return "index";
    }
}
