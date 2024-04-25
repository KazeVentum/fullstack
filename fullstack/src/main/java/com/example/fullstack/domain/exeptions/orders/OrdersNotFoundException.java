package com.example.fullstack.domain.exeptions.orders;

public class OrdersNotFoundException extends RuntimeException{
    public OrdersNotFoundException(String message){
        super(message);
    }
}
