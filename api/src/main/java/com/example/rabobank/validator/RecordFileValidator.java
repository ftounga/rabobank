package com.example.rabobank.validator;

import com.example.rabobank.domain.request.RecordRequest;
import com.example.rabobank.exception.BusinessErrorCode;
import com.example.rabobank.exception.BusinessException;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RecordFileValidator {

    public static boolean isRecordFileExtensionValid(String originalName){
        List<String> allowedExtensions = Arrays.asList("csv","xml");
        String extension = originalName.split("\\.")[1];
        return allowedExtensions.contains(extension);
    }

    public static boolean isRecordFileNameFormatValid(String originalName){
        String[] nameArray = originalName.split("\\.");
        return nameArray.length == 2;
    }

    public static void validateNoDuplicatetransactionReference(List<RecordRequest> recordRequests){
        Set<BigInteger> items = new HashSet<>();
         recordRequests.stream()
                .map(RecordRequest::getReference)
                .forEach(reference -> {
                    if (items.contains(reference)){
                        throw new BusinessException(BusinessErrorCode.REFERENCE_TRANSACTION_ALREADY_EXIST, reference.toString());
                    }
                    items.add(reference);
                });
    }
}
