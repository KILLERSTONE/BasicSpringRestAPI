package com.learn.learn.Controller.Home;

import org.springframework.web.bind.annotation.RestController;

import com.learn.learn.Model.User;
import com.learn.learn.Service.User.UserServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
public class registerController {

    @Autowired
    private UserServices userServices;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {

        boolean registered = userServices.registerUser(user);
        System.out.println(registered);
        if (registered)
            return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
        else
            return new ResponseEntity<>("User already exists", HttpStatus.BAD_REQUEST);

    }

}
