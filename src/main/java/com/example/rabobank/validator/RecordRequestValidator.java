package com.example.rabobank.validator;

import com.example.rabobank.domain.request.RecordRequest;

import java.math.BigDecimal;

public class RecordRequestValidator {

    public static boolean isEndBalanceValid(RecordRequest recordRequest){
        BigDecimal expectedEndBalance = recordRequest.getStartBalance().add(recordRequest.getMutation());
        if(expectedEndBalance.compareTo(recordRequest.getEndBalance()) !=0){
            return false;
        }
        return true;
    }
}
