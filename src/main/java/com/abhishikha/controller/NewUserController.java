package com.abhishikha.controller;

import com.abhishikha.model.Users;
import com.abhishikha.service.NewUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewUserController {

    @Autowired
    private NewUserService newUserService;

    @PostMapping("/register")
    public Users createNewUser(@RequestBody Users user){
        return newUserService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody Users user){

        return newUserService.verify(user);
    }
}
