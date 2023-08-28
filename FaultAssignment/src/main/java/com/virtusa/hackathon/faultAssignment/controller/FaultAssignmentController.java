package com.virtusa.hackathon.faultAssignment.controller;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.hackathon.faultAssignment.entity.Agent;
import com.virtusa.hackathon.faultAssignment.entity.Ticket;
import com.virtusa.hackathon.faultAssignment.service.AgentService;
import com.virtusa.hackathon.faultAssignment.service.TicketServiceImpl;


@RestController
@RequestMapping("/faultAssignment")
public class FaultAssignmentController {
	@Autowired
	private TicketServiceImpl ticketService;
	@Autowired
	private AgentService agentService;
	@GetMapping("/getAllOpenCases")
	public ResponseEntity<?> getAllOpenCases(){
	    try{
	    	List<Ticket> tl=ticketService.getTicketsByStatus("Open");
	    	if(tl==null)
		    	return new ResponseEntity<String>("No open tickets",HttpStatus.NOT_FOUND);
	    	else
	    		return new ResponseEntity<List<Ticket>>(tl,HttpStatus.OK);
	    }
	    catch (Exception e){
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	@GetMapping("/getTicket/{ticketId}")
	public ResponseEntity<?> getTicket(@PathVariable("ticketId") long ticketId){
		try{
	    	Ticket tl=ticketService.findTicketById(ticketId);
	    	if(tl==null)
		    	return new ResponseEntity<String>("No ticket with the given Id",HttpStatus.NOT_FOUND);
	    	else
	    		return new ResponseEntity<Ticket>(tl,HttpStatus.OK);
	    }
	    catch (Exception e){
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	@GetMapping("/getCustomerLevel/{ticketId}")
	public ResponseEntity<?> getCustomerLevel(@PathVariable("ticketId") long ticketId){
		try{
				Ticket t=ticketService.findTicketById(ticketId);
				if(t!=null)
					return new ResponseEntity<String>(t.getCustomer().getCustomerLevel(),HttpStatus.OK);
				else {
					return new ResponseEntity<String>("Ticket not present with the given id",HttpStatus.NOT_FOUND);
					}
	    }
	    catch (Exception e){
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	@PutMapping("assignCatAndPri/{ticketid}/{category}/{priority}")
	public ResponseEntity<?> assignCatAndPri(@PathVariable("ticketid") long ticketId,@PathVariable("category") String category,@PathVariable("priority") String priority){
		try{
				Ticket t=ticketService.findTicketById(ticketId);
				if(t!=null) {
				t.setCategory(category);
				t.setPriority(priority);
				ticketService.updateTicket(t);
		    	return new ResponseEntity<Ticket>(t,HttpStatus.OK);}
				else {
					return new ResponseEntity<String>("Ticket not present with the given id",HttpStatus.NOT_FOUND);
					}
	    }
	    catch (Exception e){
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
		
		
	}
	@PutMapping("assignAgentToTicket/{ticketid}/{agentId}")
	public ResponseEntity<?> assignCatAndPri(@PathVariable("ticketid") long ticketId,@PathVariable("agentId") long agentId){
		try{
				Ticket t=ticketService.findTicketById(ticketId);
				Agent a=agentService.getAgent(agentId);
				
				if(t!=null) {
		
						if(a!=null) {
						
						t.setAgent(a);
						t.setStatus("Assigned");
						t.getCommunicationLogs().add("a");
			    		t.setCommunicationLogs(t.getCommunicationLogs());
						a.setWorkload(a.getWorkload()+1);
						agentService.updateAgentAvailability(a);
						agentService.updateAgent(a);
						ticketService.updateTicket(t);
						
						return new ResponseEntity<Ticket>(t,HttpStatus.OK);
						}
					else {
						return new ResponseEntity<String>("Agent not present with the given id",HttpStatus.NOT_FOUND);
					}
				
		    	}
				else{
					return new ResponseEntity<String>("Ticket not present with the given id",HttpStatus.NOT_FOUND);
					}
	    }
	    catch (Exception e){
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
		
		
		
	}
	@PutMapping("/acceptTicket/{ticketId}/{value}")
	public ResponseEntity<?> acceptTicket(@PathVariable("ticketId") long ticketId,@PathVariable("value") boolean value){
		try{
			
	    	Ticket tl=ticketService.findTicketById(ticketId);
	    	if(tl==null)
		    	return new ResponseEntity<String>("No ticket with the given Id",HttpStatus.NOT_FOUND);
	    	else {
	    		if (value) {
	    		    tl.setStatus("Accepted");
	    		} else {
	    		    tl.setStatus("Rejected");
	    		}
	    		tl.getCommunicationLogs().add("A");
	    		tl.setCommunicationLogs(tl.getCommunicationLogs());
	    		tl.setAcknowledgement(true);
	    		ticketService.updateTicket(tl);
	    		return new ResponseEntity<Ticket>(tl,HttpStatus.OK);}
	    }
	    catch (Exception e){
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	@GetMapping("/getAllCompletedCases")
	public ResponseEntity<?> getAllCompletedCases(){
	    try{
	    	List<Ticket> tl=ticketService.getTicketsByStatus("Completed");
	    	if(tl==null)
		    	return new ResponseEntity<String>("No completed tickets",HttpStatus.NOT_FOUND);
	    	else
	    		return new ResponseEntity<List<Ticket>>(tl,HttpStatus.OK);
	    }
	    catch (Exception e){
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	@PutMapping("/closeTicket/{ticketId}/{value}")
	public ResponseEntity<?> closeTicket(@PathVariable("ticketId") long ticketId,@PathVariable("value") boolean value){
		try{
	    	Ticket tl=ticketService.findTicketById(ticketId);
	    	if(tl==null)
		    	return new ResponseEntity<String>("No ticket with the given Id",HttpStatus.NOT_FOUND);
	    	else {
	    		if (value) {
	    		    tl.setStatus("Closed");
	    		    Agent a=agentService.getAgent(tl.getAgent().getAgentId());
	    		    a.setWorkload(a.getWorkload()-1);
	    		    agentService.updateAgentAvailability(a);
	    		    agentService.updateAgent(a);
	    		    tl.getCommunicationLogs().add("c");
		    		tl.setCommunicationLogs(tl.getCommunicationLogs());
	    		    
	    		} else {
	    		    tl.setStatus("Open");
	    		    tl.getCommunicationLogs().add("f");
		    		tl.setCommunicationLogs(tl.getCommunicationLogs());
	    		}
	    		
	    		ticketService.updateTicket(tl);
	    		return new ResponseEntity<Ticket>(tl,HttpStatus.OK);}
	    }
	    catch (Exception e){
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	@GetMapping("/allTicketsOfagent/{agentId}")
	public List<Ticket> allTicketsOfagent(@PathVariable ("agentId") long agentId){
		return ticketService.ticketsOfAgent(agentId);
	}
	@GetMapping("/allOpenTicketsOfagent/{agentId}")
	public List<Ticket> allOpenTicketsOfagent(@PathVariable ("agentId") long agentId){
		return ticketService.allOpenTicketsOfAgent(agentId);}
}
