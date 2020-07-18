package com.example.rabobank.processor;

import com.example.rabobank.RaboBankIntegrationTest;
import com.example.rabobank.domain.request.RecordRequest;
import com.example.rabobank.exception.BusinessErrorCode;
import com.example.rabobank.exception.BusinessException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;

@RaboBankIntegrationTest
class FileParserRecordRequestTest {

    @Autowired
    private FileParserRecordRequest fileParserRecordRequest;

    @Test
    void shouldParseCsvFile() {
        String input = "181631,NL27SNSB0917829871,Tickets for Jan King,60.83,+41.96,102.79";

        RecordRequest recordRequest = fileParserRecordRequest.parseLine(input);
        Assertions.assertEquals("Tickets for Jan King", recordRequest.getDescription());
        Assertions.assertEquals(BigInteger.valueOf(181631), recordRequest.getReference());
        Assertions.assertEquals("NL27SNSB0917829871", recordRequest.getAccountNumber());
    }

    @Test
    void shouldNotParseCsvFileWithIncorrectNumberOfTokens() {
        String input = "181631,NL27SNSB0917829871,Tickets for Jan King,60.83,102.79";

        BusinessException businessException = Assertions.assertThrows(BusinessException.class, () -> fileParserRecordRequest.parseLine(input));
        Assertions.assertEquals(BusinessErrorCode.RECORD_CSV_FILE_TOKEN_INCORRECT, businessException.getBusinessErrorCode());
    }

    @Test
    void shouldNotParseCsvFileWithInconsistentEndBalance() {
        String input = "181631,NL27SNSB0917829871,Tickets for Jan King,60.83,+41.96,302.79";

        BusinessException businessException = Assertions.assertThrows(BusinessException.class, () -> fileParserRecordRequest.parseLine(input));
        Assertions.assertEquals(BusinessErrorCode.RECORD_END_BALANCE_NOT_CONSISTENT, businessException.getBusinessErrorCode());
    }

}
