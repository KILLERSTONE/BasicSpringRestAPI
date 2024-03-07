package com.learn.learn.Service.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.learn.learn.Exception.UserNotFoundException;
import com.learn.learn.Model.User;
import com.learn.learn.Service.User.UserServices;

@Service
public class AuthenticationServicesImpl implements AuthenticationServices {

    private  UserServices userServices;
    private  JwtTokenUtil jwtTokenUtil;
    private  PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationServicesImpl(UserServices userServices, JwtTokenUtil jwtTokenUtil, PasswordEncoder passwordEncoder) {
        this.userServices = userServices;
        this.jwtTokenUtil = jwtTokenUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String loginUser(String username, String password) {
        // Load user from database
        User user = userServices.loadUserByUsername(username);
        
        // Verify password
        if (user != null && passwordEncoder.matches((password), user.getPassword())) {
            // Generate JWT token for the authenticated user
            return jwtTokenUtil.generateToken(user);
        } else {
            // Authentication failed
            throw new UserNotFoundException();
        }
    }
}
