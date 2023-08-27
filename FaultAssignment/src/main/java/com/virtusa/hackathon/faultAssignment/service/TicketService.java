package com.virtusa.hackathon.faultAssignment.service;

import java.util.List;

import com.virtusa.hackathon.faultAssignment.entity.Ticket;

public interface TicketService {
	Ticket findTicketById(Long ticketId);
	List<Ticket> getTicketsByStatus(String status);
	Ticket updateTicket(Ticket ticket);
	List<Ticket> ticketsOfAgent(long agentId);
	List<Ticket> allOpenTicketsOfAgent(long agentId);
}
