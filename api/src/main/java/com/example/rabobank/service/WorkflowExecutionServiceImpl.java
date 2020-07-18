package com.example.rabobank.service;

import com.example.rabobank.domain.dto.WorkFlowExecutionDto;
import com.example.rabobank.mapper.WorkFlowExecutionMapper;
import com.example.rabobank.repository.WorkflowExecutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkflowExecutionServiceImpl implements WorkflowExecutionService {

    @Autowired
    private WorkflowExecutionRepository executionRepository;

    @Override
    public List<WorkFlowExecutionDto> getAllWorkflowExecutionDto() {
        return executionRepository.findAll()
                .stream().map(WorkFlowExecutionMapper::buildWorkflowDtoFromEntity)
                .collect(Collectors.toList());
    }
}
