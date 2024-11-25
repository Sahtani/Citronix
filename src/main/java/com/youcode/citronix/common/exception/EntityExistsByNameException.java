package com.youcode.citronix.common.exception;

public class EntityExistsByNameException extends RuntimeException {
    public EntityExistsByNameException(String name) {
        super("An entity with the name '" + name + "' already exists.");
    }
}
