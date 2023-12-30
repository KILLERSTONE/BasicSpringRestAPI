package com.learn.learn.Exception;

public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException() {
        super();
    }

    public StudentNotFoundException(String string) {
        super(string);
    }

}
