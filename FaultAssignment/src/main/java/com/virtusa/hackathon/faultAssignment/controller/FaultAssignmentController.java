package com.virtusa.hackathon.faultAssignment.controller;

import java.util.List;

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

import com.virtusa.hackathon.faultAssignment.entity.Ticket;
import com.virtusa.hackathon.faultAssignment.service.TicketServiceImpl;


@RestController
@RequestMapping("/faultAssignment")
public class FaultAssignmentController {
	@Autowired
	private TicketServiceImpl ticketService;
	@GetMapping("/getAllOpenCases")
	public ResponseEntity<?> getAllOpenCases(){
	    try{
	    	List<Ticket> tl=ticketService.getAllOpenTickets("Open");
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
}
