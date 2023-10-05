package com.mlconti.demo.exceptions;

public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(String pMessage) {
        super(pMessage);
    };

    public ObjectNotFoundException(String pMessage, Throwable ptrhow) {
        super(pMessage, ptrhow);

    }
};
