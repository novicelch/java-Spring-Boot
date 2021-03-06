package cn.lch.java_spring_boot.modules.account.service.impl;

import cn.lch.java_spring_boot.modules.account.dao.ResourceDao;
import cn.lch.java_spring_boot.modules.account.dao.RoleResourceDao;
import cn.lch.java_spring_boot.modules.account.entity.Resource;
import cn.lch.java_spring_boot.modules.account.entity.Role;
import cn.lch.java_spring_boot.modules.account.service.ResourceService;
import cn.lch.java_spring_boot.modules.common.vo.Result;
import cn.lch.java_spring_boot.modules.common.vo.SearchVo;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceDao resourceDao;
    @Autowired
    private RoleResourceDao roleResourceDao;

    @Override
    @Transactional
    public Result<Resource> editResource(Resource resource) {
        Resource resourceTemp = resourceDao.getResourceByPermission(resource.getPermission());
        if (resourceTemp != null && resourceTemp.getResourceId() != resource.getResourceId()) {
            return new Result<Resource>(Result.ResultStatus.FAILD.status, "Resource permission is repeat.");
        }

        // 添加 resource
        if (resource.getResourceId() > 0) {
            resourceDao.updateResource(resource);
        } else {
            resourceDao.addResource(resource);
        }

        // 添加 roleResource
        roleResourceDao.deleteRoleResourceByResourceId(resource.getResourceId());
        if (resource.getRoles() != null && !resource.getRoles().isEmpty()) {
            for (Role role : resource.getRoles()) {
                roleResourceDao.addRoleResource(role.getRoleId(), resource.getResourceId());
            }
        }

        return new Result<Resource>(Result.ResultStatus.SUCCESS.status, "success", resource);
    }

    @Override
    @Transactional
    public Result<Resource> deleteResource(int resourceId) {
        roleResourceDao.deleteRoleResourceByResourceId(resourceId);
        resourceDao.deleteResource(resourceId);
        return new Result<Resource>(Result.ResultStatus.SUCCESS.status, "");
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public PageInfo<Resource> getResources(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
        return new PageInfo(
                Optional.ofNullable(resourceDao.getResourcesBySearchVo(searchVo))
                        .orElse(Collections.emptyList()));
    }

    @Override
    public List<Resource> getResourcesByRoleId(int roleId) {
        return resourceDao.getResourcesByRoleId(roleId);
    }

    @Override
    public Resource getResourceById(int resourceId) {
        return resourceDao.getResourceById(resourceId);
    }
}
