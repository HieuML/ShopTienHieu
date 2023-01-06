package com.example.shoptienhieu.exception;

public class SearchByNameException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public SearchByNameException(String msg) {
        super(msg);
    }
}
