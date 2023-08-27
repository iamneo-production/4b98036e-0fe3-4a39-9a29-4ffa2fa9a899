package com.example.agent.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Agent {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long agentId;
    private String name;
    private String type;
    private Boolean availability;
    private String location;
    private String contactInformation;
    private String password;
    private List<String> skills;
    private int workload;
    private List<String> languages;
    private String shiftInformation;
	private int rating;
	public Agent(Long agentId, String name, String type, String location,
			String contactInformation, String password, List<String> skills, List<String> languages,
			String shiftInformation, int rating) {
		super();
		this.agentId = agentId;
		this.name = name;
		this.type = type;
		this.location = location;
		this.contactInformation = contactInformation;
		this.password = password;
		this.skills = skills;
		this.workload = 0;
		if(this.workload>5) {
			this.availability=false;
		}
		else {
			this.availability=true;
		}
		this.languages = languages;
		this.shiftInformation = shiftInformation;
		this.rating = rating;
	}
	
	
	
}