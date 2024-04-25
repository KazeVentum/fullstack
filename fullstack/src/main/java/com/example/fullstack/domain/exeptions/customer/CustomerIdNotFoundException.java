package com.example.fullstack.domain.exeptions.customer;

public class CustomerIdNotFoundException extends RuntimeException{
    public CustomerIdNotFoundException(String message){
        super(message);
    }
}
