package com.example.rabobank.exception;

import lombok.Data;

@Data
public class BusinessException extends RuntimeException {

    private BusinessErrorCode businessErrorCode;
    private String errorValue;

    public BusinessException(String message, BusinessErrorCode businessErrorCode, String errorValue) {
        super(message);
        this.businessErrorCode = businessErrorCode;
        this.errorValue = errorValue;
    }

    public BusinessException(BusinessErrorCode businessErrorCode, String errorValue) {
        this.businessErrorCode = businessErrorCode;
        this.errorValue = errorValue;
    }
}
