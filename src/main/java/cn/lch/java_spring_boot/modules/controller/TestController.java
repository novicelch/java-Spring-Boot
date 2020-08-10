package cn.lch.java_spring_boot.modules.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@Controller
//@RequestMapping("")
public class TestController {
    @GetMapping("hello")
    @ResponseBody
    public String hello(){
        return "Hello,World!";
    }
}
