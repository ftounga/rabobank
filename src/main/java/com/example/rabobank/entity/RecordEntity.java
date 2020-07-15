package com.example.rabobank.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "record")
public class RecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String publicId = UUID.randomUUID().toString();

    @Column
    private String accountNumber;

    @Column
    private String description;

    @Column
    private BigInteger transactionReference;

    @Column
    private BigDecimal startBalance;

    @Column
    private BigDecimal endBalance;

    @Column
    private BigDecimal mutation;

    @Column
    private LocalDate dateCreation;
}
