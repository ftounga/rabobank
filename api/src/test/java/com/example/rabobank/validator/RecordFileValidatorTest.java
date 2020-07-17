package com.example.rabobank.validator;

import com.example.rabobank.domain.request.RecordRequest;
import com.example.rabobank.exception.BusinessErrorCode;
import com.example.rabobank.exception.BusinessException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

public class RecordFileValidatorTest {

    @Test
    public void shouldNotValidateExtensionFile(){
        MultipartFile file =new MockMultipartFile("file", "test.txt", "multipart/form" ,new byte[0]);

        boolean isExtensionValid = RecordFileValidator.isRecordFileExtensionValid(file);
        Assertions.assertEquals(false, isExtensionValid);
    }

    @Test
    public void shouldValidateCsvExtensionFile(){
        MultipartFile file =new MockMultipartFile("file", "test.csv", "multipart/form" ,new byte[0]);

        boolean isExtensionValid = RecordFileValidator.isRecordFileExtensionValid(file);
        Assertions.assertEquals(true, isExtensionValid);
    }

    @Test
    public void shouldValidateXmlExtensionFile(){
        MultipartFile file =new MockMultipartFile("file", "test.xml", "multipart/form" ,new byte[0]);

        boolean isExtensionValid = RecordFileValidator.isRecordFileExtensionValid(file);
        Assertions.assertEquals(true, isExtensionValid);
    }

    @Test
    public void shoulNotValidateDuplicateTransactionReference(){
        List<RecordRequest> duplicateRecordsRecordRequest = getDuplicateRecordsRecordRequest();

        BusinessException businessException = Assertions.assertThrows(BusinessException.class, () -> RecordFileValidator.validateNoDuplicatetransactionReference(duplicateRecordsRecordRequest));
        Assertions.assertEquals(BusinessErrorCode.REFERENCE_TRANSACTION_ALREADY_EXIST, businessException.getBusinessErrorCode());
    }

    @Test
    public void shoulValidateDuplicateTransactionReference(){
        List<RecordRequest> validRecordsRecordRequest = getValidRecordsRecordRequest();

        Assertions.assertDoesNotThrow(() -> RecordFileValidator.validateNoDuplicatetransactionReference(validRecordsRecordRequest));
    }

    private List<RecordRequest> getDuplicateRecordsRecordRequest(){

        RecordRequest recordRequest1 = new RecordRequest();
        recordRequest1.setEndBalance(new BigDecimal(3.2));
        recordRequest1.setMutation(new BigDecimal(-43.2));
        recordRequest1.setStartBalance(new BigDecimal(22.8));
        recordRequest1.setAccountNumber("tfry-reez-rty");
        recordRequest1.setDescription("description");
        recordRequest1.setTransactionReference(BigInteger.valueOf(10));

        RecordRequest recordRequest2 = new RecordRequest();
        recordRequest2.setEndBalance(new BigDecimal(3.2));
        recordRequest2.setMutation(new BigDecimal(-43.2));
        recordRequest2.setStartBalance(new BigDecimal(22.8));
        recordRequest2.setAccountNumber("tfry-reez-rty");
        recordRequest2.setDescription("description");
        recordRequest2.setTransactionReference(BigInteger.valueOf(11));

        RecordRequest recordRequest3 = new RecordRequest();
        recordRequest3.setEndBalance(new BigDecimal(3.2));
        recordRequest3.setMutation(new BigDecimal(-43.2));
        recordRequest3.setStartBalance(new BigDecimal(22.8));
        recordRequest3.setAccountNumber("tfry-reez-rty");
        recordRequest3.setDescription("description");
        recordRequest3.setTransactionReference(BigInteger.valueOf(10));

        return Arrays.asList(recordRequest1, recordRequest2, recordRequest3);
    }

    private List<RecordRequest> getValidRecordsRecordRequest(){

        RecordRequest recordRequest1 = new RecordRequest();
        recordRequest1.setEndBalance(new BigDecimal(3.2));
        recordRequest1.setMutation(new BigDecimal(-43.2));
        recordRequest1.setStartBalance(new BigDecimal(22.8));
        recordRequest1.setAccountNumber("tfry-reez-rty");
        recordRequest1.setDescription("description");
        recordRequest1.setTransactionReference(BigInteger.valueOf(10));

        RecordRequest recordRequest2 = new RecordRequest();
        recordRequest2.setEndBalance(new BigDecimal(3.2));
        recordRequest2.setMutation(new BigDecimal(-43.2));
        recordRequest2.setStartBalance(new BigDecimal(22.8));
        recordRequest2.setAccountNumber("tfry-reez-rty");
        recordRequest2.setDescription("description");
        recordRequest2.setTransactionReference(BigInteger.valueOf(11));

        RecordRequest recordRequest3 = new RecordRequest();
        recordRequest3.setEndBalance(new BigDecimal(3.2));
        recordRequest3.setMutation(new BigDecimal(-43.2));
        recordRequest3.setStartBalance(new BigDecimal(22.8));
        recordRequest3.setAccountNumber("tfry-reez-rty");
        recordRequest3.setDescription("description");
        recordRequest3.setTransactionReference(BigInteger.valueOf(13));

        return Arrays.asList(recordRequest1, recordRequest2, recordRequest3);
    }
}
