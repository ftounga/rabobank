package com.example.rabobank.processor;

import com.example.rabobank.domain.dto.RecordDto;
import com.example.rabobank.domain.request.RecordRequest;
import com.example.rabobank.exception.BusinessErrorCode;
import com.example.rabobank.exception.BusinessException;
import com.example.rabobank.validator.RecordRequestValidator;

import java.math.BigDecimal;
import java.math.BigInteger;

public class FileParser {

    public static RecordRequest parseLine(String line){
        String[] recordArray = line.split(",");
        if(recordArray.length != 6){
            throw new BusinessException(BusinessErrorCode.RECORD_CSV_FILE_INCORRECT, line);
        }
        RecordRequest recordRequest = new RecordRequest();
        recordRequest.setTransactionReference(BigInteger.valueOf(Long.parseLong(recordArray[0])));
        recordRequest.setAccountNumber(recordArray[1]);
        recordRequest.setDescription(recordArray[2]);
        recordRequest.setStartBalance(BigDecimal.valueOf(Double.parseDouble(recordArray[3])));
        recordRequest.setMutation(BigDecimal.valueOf(Double.parseDouble(recordArray[4])));
        recordRequest.setEndBalance(BigDecimal.valueOf(Double.parseDouble(recordArray[5])));
        if(!RecordRequestValidator.isEndBalanceValid(recordRequest)){
            throw new BusinessException(BusinessErrorCode.RECORD_END_BALANCE_NOT_CONSISTENT, recordRequest.getEndBalance().toString());
        }
        return recordRequest;
    }
}
