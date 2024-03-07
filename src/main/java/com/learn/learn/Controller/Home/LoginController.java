package com.learn.learn.Controller.Home;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.learn.learn.Service.Security.AuthenticationServices;

import io.micrometer.core.ipc.http.HttpSender.Response;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api")
public class LoginController {
    
    @Autowired
    private AuthenticationServices authenticationServices;



    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String,String> loginRequest) {
        
        String username=loginRequest.get("username");
        String password=loginRequest.get("password");
        
        String token=authenticationServices.loginUser(username, password);
        
        if(token!=null){
            return ResponseEntity.ok(token);
        }else{
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        }
    }
    
}
