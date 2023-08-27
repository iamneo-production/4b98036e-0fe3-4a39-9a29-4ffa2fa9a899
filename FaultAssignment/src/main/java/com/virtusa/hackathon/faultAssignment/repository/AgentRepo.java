package com.virtusa.hackathon.faultAssignment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.virtusa.hackathon.faultAssignment.entity.Agent;
import com.virtusa.hackathon.faultAssignment.entity.Ticket;


public interface AgentRepo extends JpaRepository<Agent, Long> {
	Agent findByAgentId(long agentId);
	 @Query("SELECT t FROM Ticket t WHERE t.agent.agentId = :agentId AND t.status NOT IN ('Closed', 'Rated')")
	    List<Ticket> findOpenTicketsForAgent(@Param("agentId") Long agentId);
}
