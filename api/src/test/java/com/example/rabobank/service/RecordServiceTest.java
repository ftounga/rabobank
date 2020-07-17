package com.example.rabobank.service;

import com.example.rabobank.RaboBankIntegrationTest;
import com.example.rabobank.domain.dto.RecordDto;
import com.example.rabobank.domain.request.RecordRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@RaboBankIntegrationTest
public class RecordServiceTest {

    @Autowired
    private RecordService recordService;

    @Value("classpath:file/records.csv")
    Resource resourceFile;

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void shouldCreateRecord(){
        RecordRequest recordRequest = new RecordRequest();
        recordRequest.setEndBalance(new BigDecimal(3.2));
        recordRequest.setMutation(new BigDecimal(-43.2));
        recordRequest.setStartBalance(new BigDecimal(22.8));
        recordRequest.setAccountNumber("tfry-reez-rty");
        recordRequest.setDescription("description");
        recordRequest.setTransactionReference(BigInteger.valueOf(10));

        recordService.createRecord(recordRequest);
        List<RecordDto> allRecords = recordService.getAllRecords();
        Assertions.assertEquals(1, allRecords.size());
        Assertions.assertEquals("tfry-reez-rty", allRecords.get(0).getAccountNumber());
    }

    @Test
    public void shouldImportRecord() throws IOException {

        MultipartFile file =new MockMultipartFile("file", "records.csv", "multipart/form" ,resourceFile.getInputStream());
        recordService.importRecords(file);
        List<RecordDto> allRecords = recordService.getAllRecords();
        Assertions.assertEquals(10, allRecords.size());
    }
}
