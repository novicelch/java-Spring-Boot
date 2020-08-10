package cn.lch.java_spring_boot.modules.controller;

import cn.lch.java_spring_boot.modules.vo.ApplicatonTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final static Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @Value("${com.port}")
    private Integer port;
    @Value("${com.name}")
    private String name;
    @Value("${com.age}")
    private Integer age;
    @Value("${com.random}")
    private String desc;

    /**
     * http://localhost:8081/logTest
     */
    @GetMapping("/logTest")
    @ResponseBody
    public String logTest() {
        LOGGER.trace("This is trace log");
        LOGGER.debug("This is debug log");
        LOGGER.info("This is info log");
        LOGGER.warn("This is warn log");
        LOGGER.error("This is error log");
        return "This is log test";
    }
    /**
     * http://localhost:8081/config
     */
    @Autowired
    ApplicatonTest test;
    @GetMapping("config")
    @ResponseBody
    public String config(){
        String str =
                new StringBuffer().append(port).append("------").append(name)
                        .append("-------").append(age).append("-------").append(desc).append("<br>")
                .append(test.getPort()).append("-------").append(test.getName())
                        .append("---------").append(test.getAge()).append("--------").append(test.getDesc()).toString();
        return str;
    }
    /**
     *@ http://localhost:8081/hello
     */

    @GetMapping("hello")
    @ResponseBody
    public String hello(){
        return "Hello,World!";
    }
}
