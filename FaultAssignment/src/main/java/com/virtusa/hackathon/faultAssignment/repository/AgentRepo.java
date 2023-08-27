package com.virtusa.hackathon.faultAssignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.virtusa.hackathon.faultAssignment.entity.Agent;


public interface AgentRepo extends JpaRepository<Agent, Long> {

}
