package com.example.rabobank.processor;

import com.example.rabobank.domain.RecordsList;
import com.example.rabobank.domain.request.RecordRequest;
import com.example.rabobank.exception.BusinessErrorCode;
import com.example.rabobank.exception.BusinessException;
import com.example.rabobank.exception.TechnicalException;
import com.example.rabobank.validator.RecordFileValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class RecordProcessorImpl implements RecordProcessor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FileParserRecordRequest fileParserRecordRequest;

    @Autowired
    private Unmarshaller recordsUnmarshaller;

    @Autowired
    private SchemaFactory schemaFactory;

    @Override
    public Stream<RecordRequest> processRecordXmlFile(byte[] recordBuffer) {
        InputStream recordXsdStream;
        Schema schema;

        recordXsdStream = getRecordXsdStream();
        schema = getSchemaFromXsdPath(recordXsdStream);
        Validator validator = schema.newValidator();
        validateRecordXmlFile(recordBuffer, validator);
        RecordsList recordsList = parseXMLRecordsList(recordBuffer);
        RecordFileValidator.validateNoDuplicatetransactionReference(recordsList.getRecords());

        return recordsList.getRecords().stream();
    }

    private RecordsList parseXMLRecordsList(byte[] recordBuffer) {
        RecordsList recordsList;
        ByteArrayInputStream inputStream = new ByteArrayInputStream(recordBuffer);
        try {
            recordsList = (RecordsList) recordsUnmarshaller.unmarshal(inputStream);
        }catch (JAXBException ex){
            logger.error("Error occured when parsing the xml records");
            throw new BusinessException(BusinessErrorCode.RECORD_XML_PARSING_ERROR);
        }
        return recordsList;
    }

    private void validateRecordXmlFile(byte[] recordBuffer, Validator validator) {
        InputStream inputStream = new ByteArrayInputStream(recordBuffer);
        try {
            validator.validate(new StreamSource(inputStream));
        }catch (IOException ex){
            logger.error("Error occured when validatin");
            throw new TechnicalException(ex, BusinessErrorCode.TECHNICAL_ERROR);
        }catch (SAXException ex){
            throw new BusinessException(BusinessErrorCode.RECORD_XML_BAD_FORMAT_NOT_VALID);
        }
    }

    private Schema getSchemaFromXsdPath(InputStream recordXsdStream) {

        Schema schema;
        try {
            schema = schemaFactory.newSchema(new StreamSource(recordXsdStream));
        }catch (SAXException ex){
            logger.error("Cannot create schema to validate xsd file from: " +recordXsdStream);
            throw new TechnicalException(ex, BusinessErrorCode.TECHNICAL_ERROR);
        }
        return schema;
    }

    private InputStream getRecordXsdStream() {
        InputStream recordXsdStream;
        try {
            recordXsdStream = getSchemaRecordXsdStream();
        }catch (FileNotFoundException ex){
            logger.error("records xsd not found");
            throw new TechnicalException(ex, BusinessErrorCode.TECHNICAL_ERROR);
        }
        return recordXsdStream;
    }

    @Override
    public Stream<RecordRequest> processRecordCsvFile(InputStream inputStream) {
        List<RecordRequest> recordRequests =
                new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                        .lines()
                        .skip(1)
                        .map(fileParserRecordRequest::parseLine)
                .collect(Collectors.toList());
        RecordFileValidator.validateNoDuplicatetransactionReference(recordRequests);
        return recordRequests.stream();
    }

    private InputStream getSchemaRecordXsdStream() throws FileNotFoundException {
        return getClass().getClassLoader().getResourceAsStream("schema/records.xsd");
    }
}
