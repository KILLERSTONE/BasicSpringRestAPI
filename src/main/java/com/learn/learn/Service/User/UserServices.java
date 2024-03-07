package com.learn.learn.Service.User;

import com.learn.learn.Model.User;

public interface UserServices {
    
    boolean registerUser(User user);
    
    User loadUserByUsername(String username);
}
