package com.example.agent.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.agent.entity.Agent;


public interface AgentRepo extends JpaRepository<Agent, Long>{
	Agent findByAgentId(Long agentId);
	List<Agent> findByAvailabilityIsTrue();
}
