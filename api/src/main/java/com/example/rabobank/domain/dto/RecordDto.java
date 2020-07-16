package com.example.rabobank.domain.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

@Data
public class RecordDto {

    private String publicId;

    private String accountNumber;

    private String description;

    private BigInteger transactionReference;

    private BigDecimal startBalance;

    private BigDecimal endBalance;

    private BigDecimal mutation;

    private LocalDate dateCreation;
}
