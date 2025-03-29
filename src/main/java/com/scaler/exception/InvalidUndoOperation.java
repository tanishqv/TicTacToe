package com.scaler.exception;

public class InvalidUndoOperation extends RuntimeException {
    public InvalidUndoOperation(String message) {
        super(message);
    }
}
