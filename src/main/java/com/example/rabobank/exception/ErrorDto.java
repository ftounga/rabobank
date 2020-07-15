package com.example.rabobank.exception;

import lombok.Data;

@Data
public class ErrorDto {

    private String errorCode;
    private String message;
}
