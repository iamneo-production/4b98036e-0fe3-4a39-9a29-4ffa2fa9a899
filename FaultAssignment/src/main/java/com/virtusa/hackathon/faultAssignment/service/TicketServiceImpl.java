package com.virtusa.hackathon.faultAssignment.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.hackathon.faultAssignment.entity.Agent;
import com.virtusa.hackathon.faultAssignment.entity.Ticket;
import com.virtusa.hackathon.faultAssignment.repository.AgentRepo;
import com.virtusa.hackathon.faultAssignment.repository.TicketRepo;


@Service
public class TicketServiceImpl implements TicketService {
	private static final Logger logger = LoggerFactory.getLogger(TicketServiceImpl.class);

	@Autowired
	private TicketRepo ticketRepo;
	@Autowired
	private AgentRepo agentRepo;
	
	@Override
	public Ticket findTicketById(Long TicketId) {
		return ticketRepo.findByTicketId(TicketId);
	}


	@Override
	public List<Ticket> getTicketsByStatus(String status) {
		
		return ticketRepo.findByStatus(status);
	}


	@Override
	public Ticket updateTicket(Ticket ticket) {
		
		return ticketRepo.save(ticket);
	}


	@Override
	public List<Ticket> ticketsOfAgent(long agentId) {
		Agent a=agentRepo.findByAgentId(agentId);
		if(a!=null)
			return a.getTickets();
		else
			return null;
	}

	@Override
	public List<Ticket> allOpenTicketsOfAgent(long agentId) {
		Agent agent=agentRepo.findByAgentId(agentId);
		if(agent!=null) {
			return agentRepo.findOpenTicketsForAgent(agentId);
		}
		return null;
		
	}

	
}
