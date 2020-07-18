package com.example.rabobank.validator;

import com.example.rabobank.domain.request.RecordRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

public class RecordRequestValidatorTest {

    @Test
    public void shouldNotValidateEndBalance(){

        RecordRequest recordRequest = new RecordRequest();
        recordRequest.setEndBalance(new BigDecimal(3.2));
        recordRequest.setMutation(new BigDecimal(-43.2));
        recordRequest.setStartBalance(new BigDecimal(22.8));
        recordRequest.setAccountNumber("tfry-reez-rty");
        recordRequest.setDescription("description");
        recordRequest.setReference(BigInteger.valueOf(10));

        boolean endBalanceValid = RecordRequestValidator.isEndBalanceValid(recordRequest);
        Assertions.assertFalse(endBalanceValid);
    }

    @Test
    public void shouldValidateValidateEndBalance(){

        RecordRequest recordRequest = new RecordRequest();
        recordRequest.setEndBalance(new BigDecimal(6.67));
        recordRequest.setMutation(new BigDecimal(-7.25));
        recordRequest.setStartBalance(new BigDecimal(13.92));
        recordRequest.setAccountNumber("tfry-reez-rty");
        recordRequest.setDescription("description");
        recordRequest.setReference(BigInteger.valueOf(10));

        boolean endBalanceValid = RecordRequestValidator.isEndBalanceValid(recordRequest);
        Assertions.assertTrue(endBalanceValid);
    }
}
