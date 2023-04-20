package com.courcework.delivery.exception;

public class RestaurantNotFoundException extends Throwable {
    public  RestaurantNotFoundException(Long id){
        super("Ресторана с id "+id+" не существует");
    }
}
