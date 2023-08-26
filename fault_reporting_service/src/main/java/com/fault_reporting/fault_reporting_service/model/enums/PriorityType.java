package com.fault_reporting.fault_reporting_service.model.enums;

import lombok.Getter;

@Getter
public enum PriorityType {
    HIGH("high"),
    MEDIUM("medium"),
    LOW("low");

    private final String priorityType;

    private PriorityType(String priorityType) {
        this.priorityType = priorityType;
    }
}
