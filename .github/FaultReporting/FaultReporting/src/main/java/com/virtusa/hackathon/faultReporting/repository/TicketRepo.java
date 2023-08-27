package com.virtusa.hackathon.faultReporting.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.virtusa.hackathon.faultReporting.entity.Ticket;

public interface TicketRepo extends JpaRepository<Ticket, Long> {
	Ticket findByTicketId(Long ticketId);

}
