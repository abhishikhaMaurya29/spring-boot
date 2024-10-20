package com.abhishikha.service;

import com.abhishikha.model.User;
import lombok.Synchronized;
import org.springframework.stereotype.Component;

@Component
public class UserManagement {

    @Synchronized
    public String getUser(){
        User user = new User(1, "Tanu", "tanu@gmail.com", "9415753419");
        User user2 = User.builder().id(1).email("manu@gmail.com").name("Manu").mobileNo("6391311219").build();
//        System.out.println(user.setId());
        return user2.toString();
    }
}
