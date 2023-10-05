package com.mlconti.demo.exceptions;

import org.springframework.dao.DataIntegrityViolationException;

public class DataIntegryViolationException extends DataIntegrityViolationException {
    public DataIntegryViolationException(String pMessage) {
        super(pMessage);
    };

    public DataIntegryViolationException(String pMessage, Throwable ptrhow) {
        super(pMessage, ptrhow);
    };
}
