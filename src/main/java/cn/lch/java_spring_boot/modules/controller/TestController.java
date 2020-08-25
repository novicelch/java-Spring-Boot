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
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Paths;
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
     * http://localhost/test/indexSimple
     */
    @GetMapping("indexSimple")
    public String indexSimple(){
        return "indexSimple";
    }

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
    public String config() {
        String str =
                new StringBuffer().append(port).append("------").append(name)
                        .append("-------").append(age).append("-------").append(desc).append("<br>")
                        .append(test.getPort()).append("-------").append(test.getName())
                        .append("---------").append(test.getAge()).append("--------").append(test.getDesc()).toString();
        return str;
    }

    /**
     * @ http://localhost:8081/hello
     */
    @GetMapping("hello")
    @ResponseBody
    public String hello() {
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
        modelMap.addAttribute("country", country);
        modelMap.addAttribute("cities", cities);
        modelMap.addAttribute("updateCityUri", "/city");
//        modelMap.addAttribute("template", "test/index");
        return "index";
    }

    /**
     * http://localhost/test/index2
     */
    @GetMapping("/index2")
    public String testIndex2Page(ModelMap modelMap) {
//        modelMap.addAttribute("template", "test/index2");
        return "index";
    }

    /*
     * http://localhost/test/testDesc?paramKey=艹 ---- get
     */
    @GetMapping("/testDesc")
    @ResponseBody
    public String testDesc(HttpServletRequest request,
                           @RequestParam(value = "paramKey") String paramValue) {
        String paramValue2 = request.getParameter("paramKey");
        return "This is test module desc." + paramValue + "==" + paramValue2;
    }

    /**
     *  单个文件上传  localhost/test/index ---- post
     */
    @PostMapping(value = "/file", consumes = "multipart/form-data")
    public String uploadFile(@RequestParam MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select file.");
            return "redirect:index";
        }
        try {
            String destFilePath = "C:\\Users\\liuchenhu\\Desktop\\upload\\" + file.getOriginalFilename();
            File destFile = new File(destFilePath);
            file.transferTo(destFile);

            redirectAttributes.addFlashAttribute("message", "Upload file success.");
        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "Upload file failed.");
        }
        return "redirect:/test/index";
    }

    /**
     *  多个文件上传 localhost/test/index ---- post
     */
    @PostMapping(value = "/files",consumes = "multipart/form-data")
    public String uploadFiles(@RequestParam MultipartFile[] files,
                              RedirectAttributes redirectAttributes) {
        boolean empty = true;
        try {
            for (MultipartFile file : files) {
                if (file.isEmpty()) {
                    continue;
                }

                String destFilePath = "C:\\Users\\liuchenhu\\Desktop\\upload\\" + file.getOriginalFilename();
                File destFile = new File(destFilePath);
                file.transferTo(destFile);
                empty = false;
            }

            if (empty) {
                redirectAttributes.addFlashAttribute(
                        "message", "Please select file.");
            } else {
                redirectAttributes.addFlashAttribute(
                        "message", "Upload file success.");
            }

        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute(
                    "message", "Upload file failed.");
        }

        return "redirect:/test/index";
    }

    /**
     * 文件下载 localhost/test/index ---- get
     */
    @GetMapping("/file")
    public ResponseEntity<Resource> downloadFile(@RequestParam String fileName) {
        Resource resource = null;
        try {
            resource = new UrlResource(
                    Paths.get("C:\\Users\\liuchenhu\\Desktop\\upload\\" + fileName).toUri());
            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity
                        .ok()
                        .header(HttpHeaders.CONTENT_TYPE, "application/octet-stream")
                        .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s\"", resource.getFilename()))
                        .body(resource);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
