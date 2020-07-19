package com.example.rabobank.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "failed_record")
public class FailedRecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String publicId = UUID.randomUUID().toString();

    @Column
    private BigInteger transactionReference;

    @Column
    private String reason;

    @Column
    private LocalDate dateCreation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_workflow", nullable = false)
    private WorkFlowExecutionEntity workFlowExecution;
}
