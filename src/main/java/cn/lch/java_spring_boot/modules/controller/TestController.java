package cn.lch.java_spring_boot.modules.controller;

import cn.lch.java_spring_boot.modules.Service.CityService;
import cn.lch.java_spring_boot.modules.entity.City;
import cn.lch.java_spring_boot.modules.entity.Country;
import cn.lch.java_spring_boot.modules.vo.ApplicatonTest;
import cn.lch.java_spring_boot.modules.Service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private ApplicatonTest applicationTest;
    @Autowired
    private CityService cityService;
    @Autowired
    private CountryService countryServcie;

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

    /**
     * http://localhost/test/index
     */
    @GetMapping("/index")
    public String testIndexPage(ModelMap modelMap) {
        int countryId = 522;
        List<City> cities = cityService.getCitiesByCountryId(countryId);
        cities = cities.stream().limit(10).collect(Collectors.toList());
        Country country = countryServcie.getCountryByCountryId(countryId);
        modelMap.addAttribute("thymeleafTitle", "scdscsadcsacd");
        modelMap.addAttribute("checked", true);
        modelMap.addAttribute("currentNumber", 99);
        modelMap.addAttribute("changeType", "checkbox");
        modelMap.addAttribute("baiduUrl", "/test/log");
        modelMap.addAttribute("city", cities.get(0));
        modelMap.addAttribute("shopLogo",
                "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2019540177,2384259517&fm=26&gp=0.jpg");
        modelMap.addAttribute("template", "test/index");
        return "index";
    }
    /**
     * http://localhost/test/index2
     */
    @GetMapping("/index2")
    public String testIndex2Page(ModelMap modelMap) {
        modelMap.addAttribute("template", "test/index2");
        return "index";
    }
}
