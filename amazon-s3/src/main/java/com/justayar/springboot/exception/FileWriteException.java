package com.justayar.springboot.exception;

public class FileWriteException extends RuntimeException {

    public FileWriteException(String message) {
        super(message);
    }
}