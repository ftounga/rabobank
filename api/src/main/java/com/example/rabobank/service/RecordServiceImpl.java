package com.example.rabobank.service;

import com.example.rabobank.domain.ImportRecordsResponse;
import com.example.rabobank.domain.dto.RecordDto;
import com.example.rabobank.domain.request.RecordRequest;
import com.example.rabobank.entity.RecordEntity;
import com.example.rabobank.entity.WorkFlowExecutionEntity;
import com.example.rabobank.enumeration.FileExtension;
import com.example.rabobank.enumeration.RecordImportStatus;
import com.example.rabobank.exception.BusinessErrorCode;
import com.example.rabobank.exception.BusinessException;
import com.example.rabobank.mapper.RecordMapper;
import com.example.rabobank.processor.RecordProcessor;
import com.example.rabobank.repository.RecordRepository;
import com.example.rabobank.repository.WorkflowExecutionRepository;
import com.example.rabobank.util.FileUtil;
import com.example.rabobank.util.WorkflowExecutionHelper;
import com.example.rabobank.validator.RecordFileValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class RecordServiceImpl implements RecordService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private WorkflowExecutionRepository workflowExecutionRepository;

    @Autowired
    private RecordProcessor recordProcessor;

    @Override
    public List<RecordDto> getAllRecords() {
        return recordRepository.findAll().stream().
                map(RecordMapper::buildDtoFromRecordEntity)
                .collect(Collectors.toList());
    }

    @Override
    public RecordDto createRecord(RecordRequest recordRequest) {
        WorkFlowExecutionEntity workFlowExecution =  new WorkFlowExecutionEntity();
        workFlowExecution.setDateCreation(LocalDate.now());
        workFlowExecution.setStatus(RecordImportStatus.SUCCEED);
        RecordEntity record = RecordMapper.buildEntityFromRecordRequest(recordRequest, workFlowExecution);
        workFlowExecution.setRecords(Collections.singletonList(record));
        WorkFlowExecutionEntity savedWorkflowExec = workflowExecutionRepository.save(workFlowExecution);
        return RecordMapper.buildDtoFromRecordEntity(savedWorkflowExec.getRecords().get(0));
    }

    @Override
    public ImportRecordsResponse importRecords(byte[] recordBuffer, String originalName) throws IOException {
        logger.info("****** import records file");
        Stream<RecordRequest> recordRequestStream = importRecordProcessStream(recordBuffer, originalName);
        WorkFlowExecutionEntity workFlowExecution =  new WorkFlowExecutionEntity();
        workFlowExecution.setDateCreation(LocalDate.now());
        workFlowExecution.setStatus(RecordImportStatus.SUCCEED);
        workFlowExecution.setFileExtension(FileUtil.getFileExtension(originalName));
        List<RecordEntity> recordsEntity = recordRequestStream
                .map(recordRequest -> RecordMapper.buildEntityFromRecordRequest(recordRequest, workFlowExecution))
                .collect(Collectors.toList());
        workFlowExecution.setRecords(recordsEntity);
        WorkFlowExecutionEntity savedWorkFlowExec = workflowExecutionRepository.save(workFlowExecution);
        return WorkflowExecutionHelper.buildImportResponseFromWorkflow(savedWorkFlowExec);
    }

    @Async("threadPoolTaskRecordExecutor")
    @Override
    public void importRecordsAsync(byte[] recordBuffer, WorkFlowExecutionEntity executionEntity, String originalName) throws IOException {
        logger.info("****** import async records file");

        Stream<RecordRequest> recordRequestStream = importRecordProcessStream(recordBuffer, originalName);
        List<RecordEntity> recordsEntity = recordRequestStream
                .map(recordRequest -> RecordMapper.buildEntityFromRecordRequest(recordRequest, executionEntity))
                .collect(Collectors.toList());
        executionEntity.setRecords(recordsEntity);
        executionEntity.setStatus(RecordImportStatus.SUCCEED);
        executionEntity.setFileExtension(FileUtil.getFileExtension(originalName));
        workflowExecutionRepository.save(executionEntity);
    }

    private void validateFileName(String originalName) {
        logger.info("****** validate file name");
        if(!RecordFileValidator.isRecordFileNameFormatValid(originalName)){
            throw new BusinessException(BusinessErrorCode.RECORD_FORMAT_FILE_NAME_INCORRECT, originalName);
        }
        if(!RecordFileValidator.isRecordFileExtensionValid(originalName)){
            throw new BusinessException(BusinessErrorCode.RECORD_EXTENSION_FILE_NAME_INCORRECT, originalName);
        }
    }

    private Stream<RecordRequest> importRecordProcessStream(byte[] recordBuffer, String originalName) throws IOException {
        validateFileName(originalName);
        InputStream recordStream = new ByteArrayInputStream(recordBuffer);
        FileExtension fileExtension = FileUtil.getFileExtension(originalName);
        Stream<RecordRequest> recordRequestStream = null;
        if(fileExtension ==  FileExtension.CSV){
            recordRequestStream = recordProcessor.processRecordCsvFile(recordStream);
        } else if(fileExtension ==  FileExtension.XML){
            recordRequestStream = recordProcessor.processRecordXmlFile(recordBuffer);
        }
        return recordRequestStream;
    }
}
