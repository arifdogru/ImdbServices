package com.arifdogru.imdb.exception;

public class ImdbException  extends RuntimeException{

    public ImdbException(String errorMessage){
        super(errorMessage);
    }

    @Override
    public String toString() {
        return "ValidationException{" + "actor name validation error}";
    }
}
