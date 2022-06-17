package com.cgi.restaurant.util.exception;

public class ItemAlreadyExistsException extends Exception{

    private static final long serialVersionUID = 1L;

    public ItemAlreadyExistsException(String message) {
        super(message);
    }

}
