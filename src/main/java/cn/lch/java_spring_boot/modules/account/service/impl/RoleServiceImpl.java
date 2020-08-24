package cn.lch.java_spring_boot.modules.account.service.impl;

import cn.lch.java_spring_boot.modules.account.dao.RoleDao;
import cn.lch.java_spring_boot.modules.account.dao.UserRoleDao;
import cn.lch.java_spring_boot.modules.account.entity.Role;
import cn.lch.java_spring_boot.modules.account.entity.User;
import cn.lch.java_spring_boot.modules.account.service.RoleService;
import cn.lch.java_spring_boot.modules.common.vo.Result;
import cn.lch.java_spring_boot.modules.common.vo.SearchVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleDao roleDao;
    @Autowired
    UserRoleDao userRoleDao;

    @Override
    public List<Role> getRoles() {
        return Optional.ofNullable(roleDao.getRoles())
                .orElse(Collections.emptyList());
    }

    @Override
    public PageInfo<Role> getRolesBySearchVo(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(),searchVo.getPageSize());
        return new PageInfo<Role>(
                Optional.ofNullable(roleDao.getRolesBySearchVo(searchVo)).orElse(Collections.emptyList()));
    }

    @Override
    public Result<Role> insertRole(Role role) {
        Role role1 = roleDao.getRolesByRoleName(role.getRoleName());
        if(role1!=null){
            return new Result<Role>(Result.ResultStatus.FAILD.status,"角色已存在");
        }
        roleDao.insert(role);
        return new Result<Role>(Result.ResultStatus.SUCCESS.status,"添加角色成功",role);
    }

    @Override
    public Result<Role> updateRole(Role role) {

        Role roleTemp = roleDao.getRolesByRoleName(role.getRoleName());
        if (roleTemp != null && roleTemp.getRoleId() != role.getRoleId()) {
            return new Result<Role>(
                    Result.ResultStatus.FAILD.status, "您不可以修改他人信息！！！.");
        }

        roleDao.updateRole(role);

        userRoleDao.deleteUserRoleByUserId(role.getRoleId());

        return new Result<Role>(
                Result.ResultStatus.SUCCESS.status, "修改成功.", role);
    }

    @Override
    public Role getRoleByRoleId(int roleId) {
        return roleDao.getRoleByRoleId(roleId);
    }

    @Override
    public Result<Object> deleteRole(int roleId) {
        roleDao.deleteRole(roleId);
        userRoleDao.deleteUserRoleByUserId(roleId);
        return new Result(Result.ResultStatus.SUCCESS.status, "删除成功.");
    }

}
