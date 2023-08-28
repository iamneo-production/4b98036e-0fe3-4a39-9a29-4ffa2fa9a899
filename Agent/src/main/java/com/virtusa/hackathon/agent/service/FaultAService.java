package com.virtusa.hackathon.agent.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.virtusa.hackathon.agent.dto.Ticketdto;

@FeignClient(value="FAULT-ASSIGNMENT-SERVICE",path="/faultAssignment")
public interface FaultAService {
	@GetMapping("/allTicketsOfagent/{agentId}")
	public List<Ticketdto> allTicketsOfagent(@PathVariable ("agentId") long agentId);
	
	@GetMapping("/allOpenTicketsOfagent/{agentId}")
	public List<Ticketdto> allOpenTicketsOfagent(@PathVariable ("agentId") long agentId);
}
