package com.fault_reporting.fault_reporting_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fault_reporting.fault_reporting_service.model.Fault;

@Repository
public interface FaultReportingRepository extends JpaRepository<Fault, Integer> {
    
}
