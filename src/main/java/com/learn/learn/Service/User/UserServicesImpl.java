package com.learn.learn.Service.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.learn.learn.Exception.UserNotFoundException;
import com.learn.learn.Model.User;
import com.learn.learn.Repository.UserRepo;

@Service
public class UserServicesImpl implements UserServices {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServicesImpl(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean registerUser(User user) {
        if (userRepo.findByUsername(user.getUsername()) != null) {
            return false;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return true;
    }

    @Override
    public User loadUserByUsername(String username) throws UserNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("User not found with username: " + username);
        }
        return user;
    }
}
