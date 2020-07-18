package com.example.rabobank.domain;

import com.example.rabobank.enumeration.RecordImportStatus;
import lombok.Data;

@Data
public class ImportRecordsResponse {

    private String importWorkFlowPublicId;
    private int recordsNumbers;
    private RecordImportStatus status;

}
