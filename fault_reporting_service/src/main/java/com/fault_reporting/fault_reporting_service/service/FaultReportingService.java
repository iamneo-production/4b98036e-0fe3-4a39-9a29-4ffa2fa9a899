package com.fault_reporting.fault_reporting_service.service;

import org.springframework.http.ResponseEntity;

import com.fault_reporting.fault_reporting_service.model.Fault;

public interface FaultReportingService {
    ResponseEntity<?> reportFault(Fault fault);
    
}
