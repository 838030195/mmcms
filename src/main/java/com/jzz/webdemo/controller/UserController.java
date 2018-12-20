package com.jzz.webdemo.controller;

import com.jzz.webdemo.service.UserService;
import com.jzz.webdemo.utl.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    
    @RequestMapping("/register")
    public Msg register(@RequestParam("username") String username,
                        @RequestParam("password") String password) {
        return userService.register(username, password);
    }

    @RequestMapping("/auth")
    public Msg auth(@RequestParam("username") String username,
                        @RequestParam("password") String password) {
        return userService.auth(username, password);
    }

}
