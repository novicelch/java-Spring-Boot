package cn.lch.java_spring_boot.modules.account.controller;

import cn.lch.java_spring_boot.modules.account.entity.Role;
import cn.lch.java_spring_boot.modules.account.entity.User;
import cn.lch.java_spring_boot.modules.account.service.RoleService;
import cn.lch.java_spring_boot.modules.common.vo.Result;
import cn.lch.java_spring_boot.modules.common.vo.SearchVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RoleController {
    @Autowired
    RoleService roleService;

    /**
     * 127.0.0.1/api/roles ---- get
     */
    @GetMapping("/roles")
    public List<Role> getRoles() {
        return roleService.getRoles();
    }

    /**
     * 127.0.0.1/api/roles ---- post
     */
    @PostMapping("/roles")
    public PageInfo<Role> getUsersBySearchVo(@RequestBody SearchVo searchVo) {
        return roleService.getRolesBySearchVo(searchVo);
    }

    /**
     * 127.0.0.1/api/role ---- post
     */
    @PostMapping(value = "/role", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<Role> insertRole(@RequestBody Role role) {
        return roleService.insertRole(role);
    }

    @PutMapping(value = "/role", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<Role> updateRole(@RequestBody Role role){
        return roleService.updateRole(role);
    }

    /**
     * 127.0.0.1/api/role/1 ---- get
     */
    @GetMapping("/role/{roleId}")
    public Role getRoleByRoleId(@PathVariable int roleId) {
        return roleService.getRoleByRoleId(roleId);
    }

    @DeleteMapping("/role/{roleId}")
    public Result<Object> deleteUser(@PathVariable int roleId) {
        return roleService.deleteRole(roleId);
    }
}
