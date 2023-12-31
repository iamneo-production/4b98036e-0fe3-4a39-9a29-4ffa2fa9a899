package com.virtusa.hackathon.agent.dto;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.virtusa.hackathon.agent.entity.Agent;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticketdto {
	private long ticketId;
    private String location;
    private LocalDateTime dateTime;
    private String description;
    private String status;
    private String priority;
    private String category;
    private List<String> tags;
    private boolean acknowledgement;
    private Time estimatedResolutionTime;
    @Column(length = 1000000)
    private List<String> communicationLogs;
    private String reportedChannel;
    private int rating;
    @ManyToOne(fetch = FetchType.EAGER,optional=false)
    @JoinColumn(name = "customerId")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Customerdto customer;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "agentId")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Agent agent;
    
	
}
