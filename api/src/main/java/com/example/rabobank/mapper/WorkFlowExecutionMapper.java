package com.example.rabobank.mapper;

import com.example.rabobank.domain.dto.WorkFlowExecutionDto;
import com.example.rabobank.entity.WorkFlowExecutionEntity;

public class WorkFlowExecutionMapper {

    public static WorkFlowExecutionDto buildWorkflowDtoFromEntity(WorkFlowExecutionEntity executionEntity){
        WorkFlowExecutionDto dto = new WorkFlowExecutionDto();
        dto.setDateCreation(executionEntity.getDateCreation());
        dto.setPublicId(executionEntity.getPublicId());
        dto.setRecordNumbers(executionEntity.getRecords().size());
        dto.setStatus(executionEntity.getStatus());
        dto.setFileExtension(executionEntity.getFileExtension());
        return dto;
    }
}
