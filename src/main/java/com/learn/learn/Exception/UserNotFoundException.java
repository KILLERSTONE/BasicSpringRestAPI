package com.learn.learn.Exception;

public class UserNotFoundException extends RuntimeException {
    
    public UserNotFoundException(){
        super();
    }

    public UserNotFoundException(String s){
        super(s);
    }
}
