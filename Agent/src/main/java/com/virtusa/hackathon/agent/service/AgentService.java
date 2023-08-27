package com.virtusa.hackathon.agent.service;

import java.util.List;

import com.virtusa.hackathon.agent.dto.Ticketdto;
import com.virtusa.hackathon.agent.entity.Agent;

public interface AgentService {
	Agent addAgent(Agent agent);
	void updateAgent(Agent agent);
	Agent getAgent(Long agentId);
	boolean getAvailabilityOfAgent(Long agentId);
	List<Agent> getAvailabileAgents();
	List<Agent> getAgentsOrderRating();
	void updateAvailability(Agent a);
	public void updateAgentRating(int n, float rating,Agent agent);
}
