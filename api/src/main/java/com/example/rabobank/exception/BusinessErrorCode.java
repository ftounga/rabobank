package com.example.rabobank.exception;

public enum BusinessErrorCode {

    REFERENCE_TRANSACTION_ALREADY_EXIST("error.reference.transaction.already.exist", 400),
    RECORD_FORMAT_FILE_NAME_INCORRECT("error.record.file.name.format", 400),
    RECORD_CSV_FILE_TOKEN_INCORRECT("error.record.csv.file", 400),
    RECORD_END_BALANCE_NOT_CONSISTENT("error.record.end.balance.not.consistent", 400),
    TECHNICAL_ERROR("error.technical.message", 500),
    RECORD_XML_BAD_FORMAT_NOT_VALID("error.record.xml.format.incorrect", 400),
    RECORD_XML_PARSING_ERROR("error.record.xml.parsing", 400),
    RECORD_EXTENSION_FILE_NAME_INCORRECT("error.record.file.name.extension", 400);
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
