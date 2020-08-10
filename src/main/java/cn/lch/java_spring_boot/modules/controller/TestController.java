package cn.lch.java_spring_boot.modules.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    /**
     *@ http://localhost:8080/hello
     */

    @GetMapping("hello")
    @ResponseBody
    public String hello(){
        return "Hello,World!";
    }
}
