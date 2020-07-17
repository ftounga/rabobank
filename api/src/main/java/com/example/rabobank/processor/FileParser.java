package com.example.rabobank.processor;

import com.example.rabobank.domain.request.RecordRequest;

public interface FileParser<T> {
    T parseLine(String line);
}
