package com.example.fullstack.domain.exeptions.orders;

public class OrdersIdNotFoundException extends RuntimeException{
    public OrdersIdNotFoundException (String message){
        super(message);
    }
}
