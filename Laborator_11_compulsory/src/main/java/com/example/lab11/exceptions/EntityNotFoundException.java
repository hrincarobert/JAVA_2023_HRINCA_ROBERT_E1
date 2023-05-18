package com.example.lab11.exceptions;
public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String entityName, Long id) {
        super(String.format("%s with id %d not found", entityName, id));
    }

    public EntityNotFoundException(String entityName, String name) {
        super(String.format("%s %s not found", entityName, name));
    }

    public EntityNotFoundException(String entityName) {
        super(String.format("%s not found", entityName));
    }
}

