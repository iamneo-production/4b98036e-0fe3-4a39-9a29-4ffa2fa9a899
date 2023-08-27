package com.virtusa.hackathon.faultReporting.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.virtusa.hackathon.faultReporting.entity.Agent;


@FeignClient(value="AGENT-SERVICE",path="/agent")
public interface AgentService {
	@PutMapping("/updateRating/{rating}")
	public ResponseEntity<?> updateRating(@RequestBody Agent a,@PathVariable("rating") float rating);
}
