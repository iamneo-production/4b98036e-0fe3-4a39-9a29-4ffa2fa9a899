package com.virtusa.hackathon.agent.entity;

import java.util.List;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;

import com.virtusa.hackathon.agent.dto.Ticketdto;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Agent {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long agentId;
    private String name;
    private String type;
    private Boolean availability=true;
    private String location;
    private String contactInformation;
    private String password;
    private List<String> skills;
    private int workload=0;
    private List<String> languages;
    private String shiftInformation;
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 1, fraction = 1)
	private float rating;
	 
	
	
}