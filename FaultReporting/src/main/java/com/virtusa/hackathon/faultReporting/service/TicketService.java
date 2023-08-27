package com.virtusa.hackathon.faultReporting.service;

import java.util.List;

import com.virtusa.hackathon.faultReporting.entity.Ticket;

public interface TicketService {
	public void createTicket(Ticket t);
	public void updateTicketStatus(Ticket ticket,String status);
	public void updateAssignment(Ticket ticket,String Category,String Priority);
	public Ticket findTicketById(Long TicketId);
	public List<Ticket> getClosedTicketsOfCust(long custId);
}
