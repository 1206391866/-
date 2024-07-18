package com.wh.bank.exception;

public class APIException  extends RuntimeException{
    public APIException(String message) {
        super(message);
    }
}
