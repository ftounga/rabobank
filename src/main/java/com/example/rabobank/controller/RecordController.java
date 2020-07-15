package com.example.rabobank.controller;


import com.example.rabobank.config.Swagger;
import com.example.rabobank.domain.dto.RecordDto;
import com.example.rabobank.domain.request.RecordRequest;
import com.example.rabobank.service.RecordService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


@RestController
public class RecordController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RecordService recordService;

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


}
