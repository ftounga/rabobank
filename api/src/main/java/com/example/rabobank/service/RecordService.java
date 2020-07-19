package com.example.rabobank.service;

import com.example.rabobank.domain.ImportRecordsResponse;
import com.example.rabobank.domain.dto.RecordDto;
import com.example.rabobank.domain.request.RecordRequest;
import com.example.rabobank.entity.WorkFlowExecutionEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Future;

public interface RecordService {

    List<RecordDto> getAllRecords();

    RecordDto createRecord(RecordRequest recordRequest);

    ImportRecordsResponse importRecords(byte[] recordBuffer, String originalName) throws IOException;

    void importRecordsAsync(byte[] recordBuffer, WorkFlowExecutionEntity entity, String originalName) throws IOException;
}
