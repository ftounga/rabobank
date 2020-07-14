package com.example.rabobank.controller;


import com.example.rabobank.config.Swagger;
import com.example.rabobank.domain.dto.DeliveryCustomerDto;
import com.example.rabobank.service.RecordService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/delivery")
public class RecordController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RecordService recordService;

    @Swagger
    @ApiOperation("Get all records ")
    @GetMapping
    public ResponseEntity<List<DeliveryCustomerDto>> getAllDeliveryCustomer() {

        logger.info("****** get all records");
        return ResponseEntity.ok(recordService.getRecordsByCustomer(1L));
    }
}
