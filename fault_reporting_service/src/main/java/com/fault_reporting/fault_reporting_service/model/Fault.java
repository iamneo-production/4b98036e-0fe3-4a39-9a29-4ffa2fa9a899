package com.fault_reporting.fault_reporting_service.model;

import java.time.LocalDateTime;

import com.fault_reporting.fault_reporting_service.model.enums.PriorityType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Fault {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer faultId;
    private String issue;
    private String category;
    private String description;
    private Integer priority;
    private String severity;
    private Integer customerId;
    private String status;

    @CreationTimestamp
    private LocalDateTime reportedDateTime;
}
