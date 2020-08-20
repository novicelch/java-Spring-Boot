package cn.lch.java_spring_boot.modules.account.service.impl;

import cn.lch.java_spring_boot.modules.account.dao.UserDao;
import cn.lch.java_spring_boot.modules.account.entity.User;
import cn.lch.java_spring_boot.modules.account.service.UserService;
import cn.lch.java_spring_boot.modules.common.vo.Result;
import cn.lch.java_spring_boot.modules.common.vo.SearchVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Override
    public Result<User> insertUser(User user) {
        User user1 = userDao.selectByUserName(user.getUserName());
        if(user1!=null){
            return new Result<User>(Result.ResultStatus.FAILD.status,"用户名已存在");
        }
        user.setCreateDate(LocalDateTime.now());
        userDao.insert(user);
        return new Result<User>(Result.ResultStatus.SUCCESS.status,"注册成功",user);
    }

    @Override
    public Result<User> login(User user) {
        User user1 = userDao.selectByUserName(user.getUserName());
        if (user1!=null && user1.getPassword().equals(user.getPassword())){
            return new Result<User>(Result.ResultStatus.SUCCESS.status,"登录成功",user1);
        }
        return new Result<User>(Result.ResultStatus.FAILD.status,"账号或密码错误");
    }

    @Override
    public PageInfo<User> getUsersBySearchVo(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(),searchVo.getPageSize());
        return new PageInfo<User>(
                Optional.ofNullable(userDao.getUsersBySearchVo(searchVo)).orElse(Collections.emptyList()));
    }
}
