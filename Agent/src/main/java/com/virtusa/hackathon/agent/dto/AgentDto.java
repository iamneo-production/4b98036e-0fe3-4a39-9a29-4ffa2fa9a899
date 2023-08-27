package com.virtusa.hackathon.agent.dto;

import java.util.List;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class AgentDto {
	
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
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 1, fraction = 1)
	private float rating;
	 @OneToMany(mappedBy = "agent", fetch = FetchType.LAZY)
	 @ElementCollection
	private List<Ticketdto> tickets;
	public AgentDto(Long agentId, String name, String type, String location,
			String contactInformation, String password, List<String> skills,  List<String> languages,
			String shiftInformation, int rating, List<Ticketdto> tickets) {
		super();
		this.agentId = agentId;
		this.name = name;
		this.type = type;
		this.availability = availability;
		this.location = location;
		this.contactInformation = contactInformation;
		this.password = password;
		this.skills = skills;
		this.workload = 0;
		this.availability=true;
		this.languages = languages;
		this.shiftInformation = shiftInformation;
		this.rating = rating;
		this.tickets = tickets;
	}
	
	
}
