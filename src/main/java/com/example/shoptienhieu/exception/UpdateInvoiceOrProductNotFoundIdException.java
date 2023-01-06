package com.example.shoptienhieu.exception;

import lombok.Getter;

@Getter
public class UpdateInvoiceOrProductNotFoundIdException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    private String id;
    public UpdateInvoiceOrProductNotFoundIdException(String msg, String id) {
        super(msg);
        this.id = id;
    }


}
