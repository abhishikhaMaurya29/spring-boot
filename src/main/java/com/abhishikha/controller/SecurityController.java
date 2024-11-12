package com.abhishikha.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @GetMapping("/getSessionId")
    public String getSessionId(HttpServletRequest request) {
        return "Learn Spring Security, starting with the session Id : " + request.getSession().getId() + " : " +
                request.getSession().getCreationTime();
    }

    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }
}
