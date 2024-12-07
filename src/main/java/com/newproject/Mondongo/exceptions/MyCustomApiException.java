package com.newproject.Mondongo.exceptions;

public class MyCustomApiException extends RuntimeException{

    public MyCustomApiException(String message) {
        super(message);
    }
}
