package com.abhishikha;

import com.abhishikha.service.Greeting;
import com.abhishikha.service.LoginResultManagement;
import com.abhishikha.service.UserManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.util.Locale;

@SpringBootApplication
@RestController
public class Main {
    @Autowired
    private Greeting greeting;
    @Autowired
    private UserManagement userManagement;
    @Autowired
    private LoginResultManagement loginResultManagement;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

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