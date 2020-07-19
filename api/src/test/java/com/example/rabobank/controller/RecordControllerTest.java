package com.example.rabobank.controller;

import com.example.rabobank.RaboBankIntegrationTest;
import com.example.rabobank.exception.BusinessErrorCode;
import com.example.rabobank.exception.BusinessException;
import com.example.rabobank.service.RecordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RaboBankIntegrationTest
@AutoConfigureMockMvc
public class RecordControllerTest {

    @MockBean
    private RecordService recordService;

    @Value("classpath:file/records.csv")
    Resource resourceFile;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void shouldImportRecord() throws Exception {
        MockMultipartFile recordFile =new MockMultipartFile("file", "records.csv", "multipart/form" ,resourceFile.getInputStream());
        mockMvc.perform(MockMvcRequestBuilders.multipart("/records")
               .file(recordFile))
               .andExpect(status().is(200));
    }

    @Test
    public void shouldNotImportRecord() throws Exception {
        MockMultipartFile recordFile =new MockMultipartFile("file", "records.csv", "multipart/form" ,resourceFile.getInputStream());
        BusinessException businessException = new BusinessException(BusinessErrorCode.REFERENCE_TRANSACTION_ALREADY_EXIST, "5678");
        doThrow(businessException).when(recordService).importRecords(any(), any());
        mockMvc.perform(MockMvcRequestBuilders.multipart("/records")
                .file(recordFile))
                .andExpect(status().is(400));
    }
}
