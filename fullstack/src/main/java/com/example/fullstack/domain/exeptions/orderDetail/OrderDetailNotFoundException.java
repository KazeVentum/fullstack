package com.example.fullstack.domain.exeptions.orderDetail;

public class OrderDetailNotFoundException extends RuntimeException{
    public OrderDetailNotFoundException(String message){
        super(message);
    }
}
