package cn.lch.java_spring_boot.modules.account.controller;

import cn.lch.java_spring_boot.modules.account.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ResourceController {

    @Autowired
    ResourceService  resourceService;
}
