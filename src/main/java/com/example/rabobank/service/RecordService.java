package com.example.rabobank.service;

import com.example.rabobank.domain.dto.DeliveryCustomerDto;

import java.util.List;

public interface RecordService {

    public List<DeliveryCustomerDto> getRecordsByCustomer(Long idCustomer);
}
