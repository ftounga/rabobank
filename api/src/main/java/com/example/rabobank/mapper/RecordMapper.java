package com.example.rabobank.mapper;

import com.example.rabobank.domain.dto.RecordDto;
import com.example.rabobank.domain.request.RecordRequest;
import com.example.rabobank.entity.RecordEntity;
import com.example.rabobank.entity.WorkFlowExecutionEntity;

import java.time.LocalDate;

public class RecordMapper {

    public static RecordEntity buildEntityFromRecordRequest(RecordRequest recordRequest, WorkFlowExecutionEntity executionEntity){
        RecordEntity entity = new RecordEntity();
        entity.setAccountNumber(recordRequest.getAccountNumber());
        entity.setDescription(recordRequest.getDescription());
        entity.setEndBalance(recordRequest.getEndBalance());
        entity.setStartBalance(recordRequest.getStartBalance());
        entity.setMutation(recordRequest.getMutation());
        entity.setTransactionReference(recordRequest.getReference());
        entity.setDateCreation(LocalDate.now());
        entity.setWorkFlowExecution(executionEntity);
        return entity;
    }

    public static RecordDto buildDtoFromRecordEntity(RecordEntity recordEntity){

        RecordDto dto = new RecordDto();
        dto.setAccountNumber(recordEntity.getAccountNumber());
        dto.setDescription(recordEntity.getDescription());
        dto.setEndBalance(recordEntity.getEndBalance());
        dto.setStartBalance(recordEntity.getStartBalance());
        dto.setMutation(recordEntity.getMutation());
        dto.setTransactionReference(recordEntity.getTransactionReference());
        dto.setPublicId(recordEntity.getPublicId());
        dto.setDateCreation(recordEntity.getDateCreation());
        return dto;
    }
}
