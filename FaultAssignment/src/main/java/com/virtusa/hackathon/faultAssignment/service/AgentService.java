package com.virtusa.hackathon.faultAssignment.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.virtusa.hackathon.faultAssignment.entity.Agent;

@FeignClient(value="AGENT-SERVICE",path="/agent")
public interface AgentService {
	@GetMapping("/getAgent/{agentId}")
	public Agent getAgent(@PathVariable("agentId") Long agentId);
	
	@RequestMapping("/updateAgentAvailability")
	public void updateAgentAvailability(@RequestBody Agent a );
	
	@PutMapping("/updateAgent")
	public ResponseEntity<?> updateAgent(@RequestBody Agent a );
}
