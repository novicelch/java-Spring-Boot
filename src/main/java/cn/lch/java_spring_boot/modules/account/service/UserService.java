package cn.lch.java_spring_boot.modules.account.service;

import cn.lch.java_spring_boot.modules.account.entity.User;
import cn.lch.java_spring_boot.modules.common.vo.Result;
import cn.lch.java_spring_boot.modules.common.vo.SearchVo;
import com.github.pagehelper.PageInfo;

public interface UserService {
    Result<User> insertUser(User user);

    Result<User> login (User user);

    PageInfo<User> getUsersBySearchVo(SearchVo searchVo);
}
