package com.example.rabobank.service;

import com.example.rabobank.domain.dto.WorkFlowExecutionDto;
import com.example.rabobank.entity.WorkFlowExecutionEntity;

import java.util.List;

public interface WorkflowExecutionService {

    List<WorkFlowExecutionDto> getAllWorkflowExecutionDto();

    WorkFlowExecutionEntity createWorkFlowExecution();
}
