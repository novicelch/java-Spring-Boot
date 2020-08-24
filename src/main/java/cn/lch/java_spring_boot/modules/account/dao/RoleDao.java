package cn.lch.java_spring_boot.modules.account.dao;

import cn.lch.java_spring_boot.modules.account.entity.Role;
import cn.lch.java_spring_boot.modules.account.entity.User;
import cn.lch.java_spring_boot.modules.common.vo.SearchVo;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface RoleDao {

    @Select("select * from role r left join user_role ur on r.role_id = ur.role_id " +
            "where ur.user_id = #{userId} ")
    List<Role> getRolesByUserId(int userId);

    @Select("select * from role")
    List<Role> getRoles();

    @Select("<script>" +
            "select * from role "
            + "<where> "
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and (role_name like '%${keyWord}%') "
            + "</if>"
            + "</where>"
            + "<choose>"
            + "<when test='orderBy != \"\" and orderBy != null'>"
            + " order by ${orderBy} ${sort}"
            + "</when>"
            + "<otherwise>"
            + " order by role_id desc"
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    List<Role> getRolesBySearchVo(SearchVo searchVo);

    @Select("select * from role where role_name = #{roleName}")
    Role getRolesByRoleName(String roleName);

    @Insert("insert into role(role_name)" +
            "values (#{roleName})")
    @Options(useGeneratedKeys = true,keyColumn = "role_id",keyProperty = "roleId")
    void insert(Role role);

    @Update("update role set role_name = #{roleName} " +
            "where role_id = #{roleId}")
    void updateRole(Role role);


    @Select("select * from role where role_id = #{roleId}")
    Role getRoleByRoleId(int roleId);

    @Delete("delete from role where role_id = #{roleId}")
    void deleteRole(int roleId);

    @Select("select * from role r left join role_resource rr on r.role_id = rr.role_id where rr.resource_id = #{resourceId}")
    List<Role> getRolesByResourceId(int resourceId);

    @Select("select * from role where role_name = #{roleName} limit 1")
    Role getRoleByRoleName(String roleName);
}
