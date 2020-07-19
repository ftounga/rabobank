package com.example.rabobank.converter;

import com.example.rabobank.enumeration.RecordImportStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import static java.util.Objects.nonNull;

@Converter
public class RecordImportStatusConverter implements AttributeConverter<RecordImportStatus, String> {

    @Override
    public String convertToDatabaseColumn(RecordImportStatus status) {
        return nonNull(status) ? status.getValue() : null;
    }

    @Override
    public RecordImportStatus convertToEntityAttribute(String status) {
        return nonNull(status) ? RecordImportStatus.fromValue(status) : null;
    }
}
