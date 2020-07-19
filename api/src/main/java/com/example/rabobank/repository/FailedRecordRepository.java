package com.example.rabobank.repository;

import com.example.rabobank.entity.FailedRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FailedRecordRepository extends JpaRepository<FailedRecordEntity, Long> {
}
