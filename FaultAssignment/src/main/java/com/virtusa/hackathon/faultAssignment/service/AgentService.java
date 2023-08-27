package com.virtusa.hackathon.faultAssignment.service;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value="AGENT-SERVICE",path="/agent")
public interface AgentService {

}
