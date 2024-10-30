package com.abhishikha.service;

import com.abhishikha.model.LoginResult;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;

@Component
public class LoginResultManagement {
    public String getLoginResult() throws MalformedURLException {
        LoginResult loginResult = new LoginResult(
                Instant.now(),                // loginTs
                "asdasd",                     // authToken
                Duration.ofHours(1),         // tokenValidity
                new URL("https://example.com/refresh") // tokenRefreshUrl
        );

        return "Login Timestamp: " + loginResult.loginTs()
                + ", Auth Token: " + loginResult.authToken()
                + ", Token Validity: " + loginResult.tokenValidity()
                + ", Token Refresh URL: " + loginResult.tokenRefreshUrl();
    }
}
