package cn.lch.java_spring_boot.modules.account.service.impl;

import cn.lch.java_spring_boot.modules.account.dao.ResourceDao;
import cn.lch.java_spring_boot.modules.account.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    ResourceDao resourceDao;

}
