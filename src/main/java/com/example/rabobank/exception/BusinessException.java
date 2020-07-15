package com.example.rabobank.exception;

import lombok.Data;

@Data
public class BusinessException extends RuntimeException {

    private BusinessErrorCode businessErrorCode;

    public BusinessException(String message, BusinessErrorCode businessErrorCode) {
        super(message);
        this.businessErrorCode = businessErrorCode;
    }

    public BusinessException(BusinessErrorCode businessErrorCode) {
        this.businessErrorCode = businessErrorCode;
    }
}
