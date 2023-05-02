package com.courcework.delivery.exception;

public class CartNotFoundException extends Throwable {
    public  CartNotFoundException(Long id){
        super("Корзины с id "+id+" не существует");
    }
}
