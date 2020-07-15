package com.example.rabobank.service;

import com.example.rabobank.domain.dto.RecordDto;
import com.example.rabobank.domain.request.RecordRequest;

import java.util.List;

public interface RecordService {

    public List<RecordDto> getAllRecords();

    public RecordDto createRecord(RecordRequest recordRequest);
}
