package com.example.rabobank.converter;

import com.example.rabobank.enumeration.FileExtension;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import static java.util.Objects.nonNull;

@Converter
public class FileExtensionConverter implements AttributeConverter<FileExtension, String> {

    @Override
    public String convertToDatabaseColumn(FileExtension fileExtension) {
        return nonNull(fileExtension) ? fileExtension.getExtension() : null;
    }

    @Override
    public FileExtension convertToEntityAttribute(String fileExtension) {
        return nonNull(fileExtension) ? FileExtension.fromExtension(fileExtension) : null;
    }
}
