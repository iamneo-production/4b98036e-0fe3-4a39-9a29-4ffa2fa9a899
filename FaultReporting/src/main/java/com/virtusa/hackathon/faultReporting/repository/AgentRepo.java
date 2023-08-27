package com.virtusa.hackathon.faultReporting.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.virtusa.hackathon.faultReporting.entity.Agent;

public interface AgentRepo extends JpaRepository<Agent, Long> {

}
