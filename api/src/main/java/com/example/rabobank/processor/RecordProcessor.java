package com.example.rabobank.processor;

import com.example.rabobank.domain.request.RecordRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.stream.Stream;

public interface RecordProcessor {

    Stream<RecordRequest> processRecordXmlFile(byte[] recordBuffer) throws IOException;

    Stream<RecordRequest> processRecordCsvFile(InputStream inputStream);
}
