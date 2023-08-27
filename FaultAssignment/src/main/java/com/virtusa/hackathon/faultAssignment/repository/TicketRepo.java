package com.virtusa.hackathon.faultAssignment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.virtusa.hackathon.faultAssignment.entity.Ticket;


public interface TicketRepo extends JpaRepository<Ticket, Long> {
	Ticket findByTicketId(Long ticketId);
	List<Ticket> findByStatus(String status);
}
