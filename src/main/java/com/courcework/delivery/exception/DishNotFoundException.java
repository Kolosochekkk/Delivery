package com.courcework.delivery.exception;

public class DishNotFoundException extends Throwable {
    public  DishNotFoundException(Long id){
        super("Блюда с id "+id+" не существует");
    }
}