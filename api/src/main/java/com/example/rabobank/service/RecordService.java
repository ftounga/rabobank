package com.example.rabobank.service;

import com.example.rabobank.domain.ImportRecordsResponse;
import com.example.rabobank.domain.dto.RecordDto;
import com.example.rabobank.domain.request.RecordRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface RecordService {

    List<RecordDto> getAllRecords();

    RecordDto createRecord(RecordRequest recordRequest);

    ImportRecordsResponse importRecords(MultipartFile recordFile) throws IOException;
}
