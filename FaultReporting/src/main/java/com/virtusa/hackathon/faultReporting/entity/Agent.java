package com.virtusa.hackathon.faultReporting.entity;

import java.util.List;

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
    private boolean availability;
    private String location;
    private String contactInformation;
    private String password;
    private List<String> skills;
    private int workload;
    private List<String> languages;
    private String shiftInformation;
	private int rating;
	 @OneToMany(mappedBy = "agent", fetch = FetchType.LAZY)
	 @ElementCollection
	private List<Ticket> tickets;
}