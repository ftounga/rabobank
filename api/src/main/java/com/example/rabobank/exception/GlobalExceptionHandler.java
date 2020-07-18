package com.example.rabobank.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private Environment environment;

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorDto> handleBusinessException(
            BusinessException ex) {

        ErrorDto errorDto = buildErrorDtoFromBusinessException(ex);

        return new ResponseEntity<>(errorDto, HttpStatus.resolve(ex.getBusinessErrorCode().getStatus()));
    }

    @ExceptionHandler(TechnicalException.class)
    public ResponseEntity<ErrorDto> handleTechnicalException(
            TechnicalException ex) {

        ErrorDto errorDto = buildErrorDtoFromTechnicalException(ex);
        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ErrorDto buildErrorDtoFromTechnicalException(TechnicalException ex) {

        ErrorDto dto = new ErrorDto();
        dto.setMessage(environment.getProperty("error.technical.message"));
        return dto;
    }

    private ErrorDto buildErrorDtoFromBusinessException(BusinessException ex) {
        String errorCode = ex.getBusinessErrorCode().getErrorCode();
        String errorMessage = environment.getProperty(errorCode);
        ErrorDto errorDto = new ErrorDto();
        errorDto.setErrorCode(errorCode);
        errorDto.setMessage(errorMessage);
        errorDto.setErrorValue(ex.getErrorValue());

        return errorDto;
    }
}
