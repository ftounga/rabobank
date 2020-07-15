package com.example.rabobank.domain.request;

import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.math.BigInteger;

@Data
public class RecordRequest {

    private String accountNumber;

    private String description;

    private BigInteger transactionReference;

    private BigDecimal startBalance;

    private BigDecimal endBalance;

    private BigDecimal mutation;
}

