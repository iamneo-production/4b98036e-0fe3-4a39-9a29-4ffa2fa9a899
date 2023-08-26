package com.fault_reporting.fault_reporting_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fault_reporting.fault_reporting_service.model.Fault;
import com.fault_reporting.fault_reporting_service.service.FaultReportingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("${spring.data.rest.basePath}")
public class FaultReportingController {

    private final FaultReportingService service;

    @PostMapping("/v1/fault/report")
    public ResponseEntity<?> reportFault(@RequestBody Fault fault) {
        return service.reportFault(fault);
    }
    
}
