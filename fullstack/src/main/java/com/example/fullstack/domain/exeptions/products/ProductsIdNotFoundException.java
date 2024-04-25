package com.example.fullstack.domain.exeptions.products;

public class ProductsIdNotFoundException extends RuntimeException{
    public ProductsIdNotFoundException(String message){
        super(message);
    }
}
