package com.cgi.restaurant.util.exception;

public class RestaurantAlreadyExistsException extends Exception{

    private static final long serialVersionUID = 1L;

    public RestaurantAlreadyExistsException(String message) {
        super(message);
    }

}
