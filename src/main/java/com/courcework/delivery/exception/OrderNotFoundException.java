package com.courcework.delivery.exception;

public class OrderNotFoundException extends Throwable {
    public  OrderNotFoundException(Long id){
        super("Заказа с id "+id+" не существует");
    }

}
