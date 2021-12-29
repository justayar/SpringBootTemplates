package com.justayar.springboot.exception;

public class NotExistBucketException extends RuntimeException {

    public NotExistBucketException(String message) {
        super(message);
    }
}
