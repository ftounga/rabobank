package com.example.rabobank.processor;

import com.example.rabobank.domain.request.RecordRequest;

import java.io.InputStream;
import java.util.stream.Stream;

public interface RecordProcessor {

    Stream<RecordRequest> processRecordXmlFile(InputStream inputStream);

    Stream<RecordRequest> processRecordCsvFile(InputStream inputStream);
}
