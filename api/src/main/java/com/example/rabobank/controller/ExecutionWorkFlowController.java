package com.example.rabobank.controller;

import com.example.rabobank.config.Swagger;
import com.example.rabobank.domain.dto.RecordDto;
import com.example.rabobank.domain.dto.WorkFlowExecutionDto;
import com.example.rabobank.service.RecordService;
import com.example.rabobank.service.WorkflowExecutionService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExecutionWorkFlowController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WorkflowExecutionService executionService;

    @Swagger
    @ApiOperation("Get all workflow executions records import ")
    @GetMapping(value = "/workflowexecution")
    public ResponseEntity<List<WorkFlowExecutionDto>> getAllDeliveryCustomer() {

        logger.info("****** get all workflow execution records");
        return ResponseEntity.ok(executionService.getAllWorkflowExecutionDto());
    }

}
