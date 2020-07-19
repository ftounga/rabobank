package com.example.rabobank.controller;


import com.example.rabobank.config.Swagger;
import com.example.rabobank.domain.ImportRecordsResponse;
import com.example.rabobank.domain.dto.RecordDto;
import com.example.rabobank.domain.request.RecordRequest;
import com.example.rabobank.entity.WorkFlowExecutionEntity;
import com.example.rabobank.service.RecordService;
import com.example.rabobank.service.WorkflowExecutionService;
import com.example.rabobank.util.WorkflowExecutionHelper;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;


@RestController
public class RecordController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RecordService recordService;

    @Autowired
    private WorkflowExecutionService executionService;

    @Swagger
    @ApiOperation("Get all records ")
    @GetMapping(value = "/records")
    public ResponseEntity<List<RecordDto>> getAllDeliveryCustomer() {

        logger.info("****** get all records");
        return ResponseEntity.ok(recordService.getAllRecords());
    }

    @Swagger
    @ApiOperation("save record ")
    @PostMapping(value = "/record")
    public ResponseEntity<RecordDto> sendRecord(@RequestBody @NonNull @Valid RecordRequest recordRequest) {

        logger.info("****** send record");
        return ResponseEntity.ok(recordService.createRecord(recordRequest));
    }

    @Swagger
    @ApiOperation("import records ")
    @PostMapping("/records")
    public ResponseEntity<ImportRecordsResponse> importRecords(@RequestParam("file") MultipartFile recordFile) throws IOException {

        logger.info("****** import record file");
        byte[] recordBuffer = IOUtils.toByteArray(recordFile.getInputStream());
        return ResponseEntity.ok(recordService.importRecords(recordBuffer, recordFile.getOriginalFilename()));
    }

    @Swagger
    @ApiOperation("import records asynchronously")
    @PostMapping("/async-records")
    public ResponseEntity<ImportRecordsResponse> importRecordsAsync(@RequestParam("file") MultipartFile recordFile) throws IOException {

        logger.info("****** import record file asynchronously");
        WorkFlowExecutionEntity workFlowExecution = executionService.createWorkFlowExecution();
        byte[] recordBuffer = IOUtils.toByteArray(recordFile.getInputStream());
        recordService.importRecordsAsync(recordBuffer, workFlowExecution, recordFile.getOriginalFilename());

        return ResponseEntity.ok(WorkflowExecutionHelper.buildImportResponseFromWorkflow(workFlowExecution));
    }

}
