package com.virtusa.hackathon.agent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.hackathon.agent.dto.AgentDto;
import com.virtusa.hackathon.agent.dto.Ticketdto;
import com.virtusa.hackathon.agent.entity.Agent;
import com.virtusa.hackathon.agent.repository.AgentRepo;


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

	@Override
	public List<Agent> getAgentsOrderRating() {
		
		return agentRepo.findAvailableAgentsOrderedByRating();
	}

	@Override
	public void updateAvailability(Agent a) {
		if(a.getWorkload()>5) {
			a.setAvailability(false);
		}
		a.setAvailability(true);
		
	}
	@Override
	public void updateAgentRating(int n, float rating,Agent agent) {
		agent.setRating(((agent.getRating()*(n-1))+rating)/n);
		agentRepo.save(agent);
		
	}

	

	

	
}
