package com.example.rabobank.domain.dto;

import com.example.rabobank.enumeration.FileExtension;
import com.example.rabobank.enumeration.RecordImportStatus;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class WorkFlowExecutionDto {

    private String publicId = UUID.randomUUID().toString();

    private LocalDate dateCreation;

    private RecordImportStatus status;

    private FileExtension fileExtension;

    private Integer recordNumbers;
}
