package cn.lch.java_spring_boot.modules.account.service;

import cn.lch.java_spring_boot.modules.account.entity.Role;
import cn.lch.java_spring_boot.modules.account.entity.User;
import cn.lch.java_spring_boot.modules.common.vo.Result;
import cn.lch.java_spring_boot.modules.common.vo.SearchVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface RoleService {

    List<Role> getRoles();

    PageInfo<Role> getRolesBySearchVo(SearchVo searchVo);

    Result<Role> insertRole(Role role);

    Result<Role> updateRole(Role role);

    Role getRoleByRoleId(int roleId);

    Result<Object> deleteRole(int roleId);
}
