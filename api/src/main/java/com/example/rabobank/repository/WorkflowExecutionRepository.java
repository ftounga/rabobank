package com.example.rabobank.repository;

import com.example.rabobank.entity.WorkFlowExecutionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkflowExecutionRepository extends JpaRepository<WorkFlowExecutionEntity, Long> {

}
