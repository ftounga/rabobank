package com.example.rabobank.util;

import com.example.rabobank.domain.ImportRecordsResponse;
import com.example.rabobank.entity.WorkFlowExecutionEntity;

public class WorkflowExecutionHelper {

    public static ImportRecordsResponse buildImportResponseFromWorkflow(WorkFlowExecutionEntity executionEntity) {
        ImportRecordsResponse response = new ImportRecordsResponse();
        response.setRecordsNumbers(executionEntity.getRecords().size());
        response.setStatus(executionEntity.getStatus());
        response.setImportWorkFlowPublicId(executionEntity.getPublicId());
        return  response;
    }
}
