package com.abhishikha.controller;

import com.abhishikha.service.Greeting;
import com.abhishikha.service.LoginResultManagement;
import com.abhishikha.service.UserManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.MalformedURLException;
import java.util.Locale;

public class UserController {
    @Autowired
    private Greeting greeting;
    @Autowired
    private UserManagement userManagement;
    @Autowired
    private LoginResultManagement loginResultManagement;

    @GetMapping("/")
    public String greet() {
        return "Hello";
    }

    @GetMapping("/greeting")
    public String getLocaleMessage(@RequestParam(value = "lang", defaultValue = "en") String lang) {
        return greeting.getGreeting(new Locale(lang));
    }

    @GetMapping("/user")
    public String getUser() {
        return userManagement.getUser();
    }

    @GetMapping("/loginInfo")
    public String getLoginInfo() throws MalformedURLException {
        return loginResultManagement.getLoginResult();
    }
}
