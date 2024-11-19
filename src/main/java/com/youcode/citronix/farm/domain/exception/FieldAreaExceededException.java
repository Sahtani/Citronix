package com.youcode.citronix.farm.domain.exception;

public class FieldAreaExceededException extends RuntimeException {
    public FieldAreaExceededException(String message) {
        super(message);
    }
}
