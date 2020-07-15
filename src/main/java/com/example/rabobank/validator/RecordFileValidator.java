package com.example.rabobank.validator;

import com.example.rabobank.domain.request.RecordRequest;
import com.example.rabobank.exception.BusinessErrorCode;
import com.example.rabobank.exception.BusinessException;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RecordFileValidator {

    public static boolean isRecordFileExtensionValid(MultipartFile recordFile){
        List<String> allowedExtensions = Arrays.asList("csv","xml");
        String extension = recordFile.getOriginalFilename().split("\\.")[1];
        return allowedExtensions.contains(extension);
    }

    public static boolean isRecordFileNameFormatValid(MultipartFile recordFile){
        String[] originalName = recordFile.getOriginalFilename().split("\\.");
        return originalName.length == 2;
    }

    public static void validateNoDuplicatetransactionReference(List<RecordRequest> recordRequests){
        Set<BigInteger> items = new HashSet<>();
         recordRequests.stream()
                .map(RecordRequest::getTransactionReference)
                .forEach(reference -> {
                    if (items.contains(reference)){
                        throw new BusinessException(BusinessErrorCode.REFERENCE_TRANSACTION_ALREADY_EXIST, reference.toString());
                    }
                    items.add(reference);
                });
    }
}
