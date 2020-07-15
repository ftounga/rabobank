package com.example.rabobank.exception;

public enum BusinessErrorCode {

    REFERENCE_TRANSACTION_ALREADY_EXIST("error.reference.transaction.already.exist", 400);
    private String errorCode;
    private Integer status;

    BusinessErrorCode(String errorCode, Integer status){
        this.errorCode = errorCode;
        this.status = status;
    }

    public String getErrorCode(){
        return errorCode;
    }

    public Integer getStatus(){
        return status;
    }
}
