package com.virtusa.hackathon.agent.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.virtusa.hackathon.agent.dto.Ticketdto;
import com.virtusa.hackathon.agent.entity.Agent;


public interface AgentRepo extends JpaRepository<Agent, Long>{
	Agent findByAgentId(Long agentId);
	List<Agent> findByAvailabilityIsTrue();
	
	 @Query("SELECT a FROM Agent a WHERE a.availability = true ORDER BY a.rating DESC")
	 List<Agent> findAvailableAgentsOrderedByRating();
	 
	
	 
	
	 
}