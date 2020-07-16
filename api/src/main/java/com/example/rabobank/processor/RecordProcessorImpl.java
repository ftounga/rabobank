package com.example.rabobank.processor;

import com.example.rabobank.domain.request.RecordRequest;
import com.example.rabobank.validator.RecordFileValidator;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class RecordProcessorImpl implements RecordProcessor {

    @Override
    public Stream<RecordRequest> processRecordXmlFile(InputStream inputStream){
        return null;
    }

    @Override
    public Stream<RecordRequest> processRecordCsvFile(InputStream inputStream) {
        List<RecordRequest> recordRequests =
                new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                        .lines()
                        .skip(1)
                        .map(FileParser::parseLine)
                .collect(Collectors.toList());
        RecordFileValidator.validateNoDuplicatetransactionReference(recordRequests);
        return recordRequests.stream();
    }


}
