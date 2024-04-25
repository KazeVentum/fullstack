package com.example.fullstack.domain.exeptions.orderDetail;

public class OrderDetailIdNotFoundException extends RuntimeException{
    public OrderDetailIdNotFoundException(String message){
        super(message);
    }
}
