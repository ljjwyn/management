package com.instructional.system.management.server;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.instructional.system.management.mapper.UserMapper;
import com.instructional.system.management.model.User;
import com.instructional.system.management.utils.Result;
import com.instructional.system.management.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * @author ljjwyn
 */
@Service
public class LoginServer {

    @Autowired
    private UserMapper userMapper;

    public Result verifyAccount(String userAccount, String password, HttpSession httpSession){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.select("password", "roles", "name", "position", "avatar").like("account",userAccount);
        User user = userMapper.selectOne(userQueryWrapper);
        if(user==null) {
            return Result.failure(ResultCode.LOGIN_ACCOUNT_INVALID);
        }
        if(password.equals(user.getPassword())){
            httpSession.setAttribute("user", userAccount);
            httpSession.setAttribute("token", user.getRoles());
            return Result.success(user);
        }else {
            return Result.failure(ResultCode.LOGIN_ACCOUNT_INVALID);
        }
    }

    public Result geUserToken(String userAccount){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.select("roles").like("account",userAccount);
        User user = userMapper.selectOne(userQueryWrapper);
        return Result.success(user.getRoles());
    }

    public Result getUserBasicInfo(String userAccount){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.select("name", "position", "avatar").like("account",userAccount);
        User user = userMapper.selectOne(userQueryWrapper);
        return Result.success(user);
    }
}
