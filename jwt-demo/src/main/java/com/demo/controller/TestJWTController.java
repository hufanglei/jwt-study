package com.demo.controller;

import com.demo.annotation.PassToken;
import com.demo.annotation.UserLoginToken;
import com.demo.pojo.User;
import com.demo.service.UserService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestJWTController {

    @Autowired
    private UserService userService;

    @PassToken
    @GetMapping("/login")
    public String login(User user){
        String token = userService.getToken(user);
        log.info(token);
        return token;
    }

    @UserLoginToken
    @GetMapping("/getUser")
    public User getUser(String userId){
        User user = userService.findUserById(userId);
        log.info(new Gson().toJson(user));
        return user;
    }

}
