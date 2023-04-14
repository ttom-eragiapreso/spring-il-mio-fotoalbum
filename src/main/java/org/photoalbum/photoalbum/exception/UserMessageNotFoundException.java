package org.photoalbum.photoalbum.exception;

public class UserMessageNotFoundException extends RuntimeException{
    public UserMessageNotFoundException(String message) {
        super(message);
    }
}
