package com.example.fullstack.domain.exeptions;

public class UserIdNotFoundException extends RuntimeException{
    public UserIdNotFoundException(String message){
        super(message);
    }
}
