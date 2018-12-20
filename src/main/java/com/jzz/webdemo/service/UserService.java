package com.jzz.webdemo.service;

import com.jzz.webdemo.entity.User;
import com.jzz.webdemo.mapper.UserMapper;
import com.jzz.webdemo.utl.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public Msg register(String username, String password) {
        if (username.trim().equals("null") || password.trim().equals("null") ||
                username.trim().equals("") || password.trim().equals("")) {
            return Msg.err("非法的用户名或密码");
        }
        User user = userMapper.getByUsername(username);

        if (user == null) {
            user = new User();
            user.setUsername(username);
            user.setPassword(password);
            userMapper.register(user);

            return Msg.ok("注册成功", user);
        } else {
            return Msg.err("用户名已存在");
        }
    }

    public Msg auth(String username, String password) {
        User user = userMapper.auth(username, password);
        if (user == null) {
            return Msg.err("用户名或密码错误");
        } else {
            return Msg.ok("登录成功", user);
        }
    }

}
