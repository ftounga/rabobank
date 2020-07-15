package com.example.rabobank.service;

import com.example.rabobank.domain.dto.RecordDto;
import com.example.rabobank.domain.request.RecordRequest;
import com.example.rabobank.entity.RecordEntity;
import com.example.rabobank.exception.BusinessErrorCode;
import com.example.rabobank.exception.BusinessException;
import com.example.rabobank.mapper.RecordMapper;
import com.example.rabobank.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordRepository recordRepository;

    @Override
    public List<RecordDto> getAllRecords() {
        List<RecordDto> recordsDto = recordRepository.findAll().stream().
                map(RecordMapper::buildDtoFromRecordEntity)
                .collect(Collectors.toList());
       if(recordsDto.size()>0) {
           throw new BusinessException(BusinessErrorCode.REFERENCE_TRANSACTION_ALREADY_EXIST);
       }
       return recordsDto;
    }

    @Override
    public RecordDto createRecord(RecordRequest recordRequest) {
        RecordEntity savedRecord = recordRepository.save(RecordMapper.buildEntityFromRecordRequest(recordRequest));
        return RecordMapper.buildDtoFromRecordEntity(savedRecord);
    }
}
