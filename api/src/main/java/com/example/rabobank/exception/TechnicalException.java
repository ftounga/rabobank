package com.example.rabobank.exception;

public class TechnicalException extends RuntimeException {

    private BusinessErrorCode businessErrorCode;

    public TechnicalException(Throwable throwable, BusinessErrorCode businessErrorCode){
        super(throwable);
        this.businessErrorCode = businessErrorCode;
    }
}
