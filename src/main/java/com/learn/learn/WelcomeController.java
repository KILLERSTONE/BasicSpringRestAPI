package com.learn.learn;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    
    @RequestMapping("/")
    public String hello(){
        return "index";
    }
}
