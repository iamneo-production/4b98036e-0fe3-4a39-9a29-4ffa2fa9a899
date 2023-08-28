package com.virtusa.hackathon.agent.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.virtusa.hackathon.agent.dto.Ticketdto;

@FeignClient(value="FAULT-REPORTING-SERVICE",path="/faultReporting")
public interface FaultRServiceProxy {
	@GetMapping("/getTicket/{ticketId}")
	public Ticketdto getTicket(@PathVariable("ticketId") long ticketId);
	@PutMapping("/updateTicket")
	public ResponseEntity<?> updateTicket(@RequestBody Ticketdto ticket);
	
}
