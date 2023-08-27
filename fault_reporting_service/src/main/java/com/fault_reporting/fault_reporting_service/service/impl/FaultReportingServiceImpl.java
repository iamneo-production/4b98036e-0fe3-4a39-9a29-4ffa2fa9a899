package com.fault_reporting.fault_reporting_service.service.impl;

import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fault_reporting.fault_reporting_service.model.Fault;
import com.fault_reporting.fault_reporting_service.repository.FaultReportingRepository;
import com.fault_reporting.fault_reporting_service.service.FaultReportingService;
import com.fault_reporting.fault_reporting_service.util.ResponseUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class FaultReportingServiceImpl implements FaultReportingService {

    private final FaultReportingRepository faultReportingRepository;

    @Override
    public ResponseEntity<?> reportFault(Fault fault) {
        Integer priority = getPriority(fault.getSeverity());
        fault.setPriority(priority);
       try {
            Fault reportedFault =  faultReportingRepository.save(fault);
            if(reportedFault !=null) {
                Random rnd = new Random();
                faultId = "FAULT" + rnd.nextInt(99999999);
            }
            return ResponseEntity.ok(ResponseUtil.createSuccessReponse(FaultResponse.builder.faultId(faultId).build()));
        
       } catch (Exception e) {
            log.error("Unable to save the fault {}", fault);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(ResponseUtil
                .createErrorResponse(HttpStatus.BAD_REQUEST.value(), "Unable to report the fault"));
       }
       
    }
}
