package com.virtusa.hackathon.faultReporting.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.hackathon.faultReporting.entity.Ticket;
import com.virtusa.hackathon.faultReporting.repository.TicketRepo;
@Service
public class TicketServiceImpl implements TicketService {
	private static final Logger logger = LoggerFactory.getLogger(TicketServiceImpl.class);

	@Autowired
	private TicketRepo ticketRepo;
	
	@Override
	public void createTicket(Ticket t) {
		if(t==null) {
			logger.warn("The ticket created is null");
		}else {
		ticketRepo.save(t);
		logger.info("Ticket is generated with ticket Id "+t.getTicketId());
		}
	}

	@Override
	public void updateTicketStatus(Ticket ticket,String status) {
		if(ticket==null) {
			logger.warn("The ticket is null");
		}else {
			ticket.setStatus(status);
			ticketRepo.save(ticket);
		}
		
	}

	

	@Override
	public Ticket findTicketById(Long TicketId) {
		return ticketRepo.findByTicketId(TicketId);
	}

	

	@Override
	public void updateAssignment(Ticket ticket, String category, String priority) {
		if(ticket==null) {
			logger.warn("The ticket is null");
		}else {
			ticket.setCategory(category);
			ticket.setPriority(priority);
		
			ticketRepo.save(ticket);
		}
	}

	@Override
	public List<Ticket> getClosedTicketsOfCust(long custId) {
		return ticketRepo.findClosedTicketsByCustomer(custId);
	}
}
