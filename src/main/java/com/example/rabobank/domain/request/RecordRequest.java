package com.example.rabobank.domain.request;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.BigInteger;

@Data
public class RecordRequest {

    @NotNull
    private String accountNumber;

    private String description;

    @NotNull
    private BigInteger transactionReference;

    @NotNull
    private BigDecimal startBalance;

    @NotNull
    private BigDecimal endBalance;

    @NotNull
    private BigDecimal mutation;
}

