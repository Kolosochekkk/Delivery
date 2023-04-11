package com.courcework.delivery.exception;

public class UserNotFoundException extends RuntimeException{
    public  UserNotFoundException(Long id){
        super("Пользователя с id "+id+" не существует");
    }

}
