package com.example.rabobank.service;

import com.example.rabobank.domain.dto.DeliveryCustomerDto;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {

    @Override
    public List<DeliveryCustomerDto> getRecordsByCustomer(Long idCustomer) {
        return Collections.emptyList();
    }
}
