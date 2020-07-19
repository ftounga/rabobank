package com.example.rabobank.entity;


import com.example.rabobank.converter.FileExtensionConverter;
import com.example.rabobank.converter.RecordImportStatusConverter;
import com.example.rabobank.enumeration.FileExtension;
import com.example.rabobank.enumeration.RecordImportStatus;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "workflow_execution")
public class WorkFlowExecutionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String publicId = UUID.randomUUID().toString();

    @Column
    private LocalDate dateCreation;

    @Convert(converter = RecordImportStatusConverter.class)
    private RecordImportStatus status;

    @Convert(converter = FileExtensionConverter.class)
    private FileExtension fileExtension;

    @OneToMany(mappedBy = "workFlowExecution", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<RecordEntity> records = new ArrayList<>();

    @OneToMany(mappedBy = "workFlowExecution", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<FailedRecordEntity> failedRecords = new ArrayList<>();
}
