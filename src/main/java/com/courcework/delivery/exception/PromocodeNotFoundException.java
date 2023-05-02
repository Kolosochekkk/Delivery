package com.courcework.delivery.exception;

public class PromocodeNotFoundException extends Throwable {
    public  PromocodeNotFoundException(Long id){
        super("Промокода с id "+id+" не существует");
    }
}