package com.example.agent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.agent.entity.Agent;
import com.example.agent.repository.AgentRepo;


@Service
public class AgentServiceImpl implements AgentService {
	@Autowired
	private AgentRepo agentRepo;
	@Override
	public Agent addAgent(Agent agent) {
		return agentRepo.save(agent);
	}

	@Override
	public void updateAgent(Agent agent) {
		agentRepo.save(agent);
	}

	@Override
	public Agent getAgent(Long agentId) {
		return agentRepo.findByAgentId(agentId);
	}

	@Override
	public boolean getAvailabilityOfAgent(Long agentId) {
		return agentRepo.findByAgentId(agentId).getAvailability();
	}

	@Override
	public List<Agent> getAvailabileAgents() {
		return agentRepo.findByAvailabilityIsTrue();
	}

	
}
