package com.example.agent.service;

import java.util.List;

import com.example.agent.entity.Agent;

public interface AgentService {
	Agent addAgent(Agent agent);
	void updateAgent(Agent agent);
	Agent getAgent(Long agentId);
	boolean getAvailabilityOfAgent(Long agentId);
	List<Agent> getAvailabileAgents();
}
