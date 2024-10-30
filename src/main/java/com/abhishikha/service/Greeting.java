package com.abhishikha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class Greeting {
    @Autowired
    private MessageSource messageSource;

    public String getGreeting(Locale locale) {
        return messageSource.getMessage("greeting", null, locale) + ", " + messageSource.getMessage("welcome", null, locale) + " in " + messageSource.getMessage("location", null, locale);
    }
}
