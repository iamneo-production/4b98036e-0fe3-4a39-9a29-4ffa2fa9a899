package com.virtusa.hackathon.faultReporting.controller;


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

import com.virtusa.hackathon.faultReporting.entity.Customer;
import com.virtusa.hackathon.faultReporting.entity.Ticket;
import com.virtusa.hackathon.faultReporting.service.AgentService;
import com.virtusa.hackathon.faultReporting.service.CustomerServiceImpl;
import com.virtusa.hackathon.faultReporting.service.TicketServiceImpl;

@RestController
@RequestMapping("/faultReporting")
public class FaultReportingController {
	@Autowired
	private CustomerServiceImpl customerService;
	@Autowired
	private TicketServiceImpl ticketService;
	@Autowired
	private AgentService agentService;
	@PostMapping("/addCustomer")
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer){
	    try{
	        customerService.addCustomer(customer);
	        return new ResponseEntity<Customer>(customer, HttpStatus.CREATED);
	    }
	    catch (Exception e){
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	}
	@GetMapping("/customerLogin/{email}/{password}")
	public ResponseEntity<?> customerLogin(@PathVariable("email") String email,@PathVariable("password") String password){
		
		try{
			Customer c=customerService.login(email, password);
			if(c!=null) {
				return new ResponseEntity<Customer>(c, HttpStatus.CREATED);}
			else
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	    }
	    catch (Exception e){
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	@GetMapping("/getCustomer/{customerId}")
	public ResponseEntity<?> customerLogin(@PathVariable("customerId") long customerId){
		
		try{
			Customer c=customerService.getCustomer(customerId);
			if(c!=null) {
				return new ResponseEntity<Customer>(c, HttpStatus.OK);}
			else
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	    }
	    catch (Exception e){
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	@PutMapping("/update/{customerEmail}")
	public ResponseEntity<?> updateCustomer(@RequestBody Customer customer, @PathVariable String customerEmail){
	    try{
	    	customer.setCustomerEmail(customerEmail);
	        customerService.updateCustomer(customer);
	        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	    }
	    catch (Exception e){
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	@PostMapping("/createTicket")
	public ResponseEntity<?> createTicket(@RequestBody Ticket ticket) {
		try{
			ticket.setStatus("Open");
			ticket.getCommunicationLogs().add("o");
			ticket.setCommunicationLogs(ticket.getCommunicationLogs());
			ticketService.createTicket(ticket);
	        return new ResponseEntity<Ticket>(ticket, HttpStatus.CREATED);
	    }
	    catch (Exception e){
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	@PutMapping("/withdrawRequest")
	public ResponseEntity<?> withdrawRequest(@RequestBody Ticket ticket) {
		try{
			ticketService.updateTicketStatus(ticket, "Closed");
	        return new ResponseEntity<Ticket>(ticket, HttpStatus.OK);
	    }
	    catch (Exception e){
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	@GetMapping("/getAllTicketsOfCust/{customerId}")
	public List<Ticket> getAllTicketsOfCust(@PathVariable("customerId") long customerid){
	       Customer c=customerService.getCustomer(customerid);
	        return c.getTickets();
	   
	}
	@GetMapping("/getClosedTicketsOfCust/{customerId}")
	public ResponseEntity<?> getClosedTicketsOfCust(@PathVariable("customerId") long customerid){
		try{
	        return new ResponseEntity<List<Ticket>>(ticketService.getTicketsOfCustByStatus(customerid,"Closed"), HttpStatus.OK);
	    }
	    catch (Exception e){
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	   
	}
	@PutMapping("/addCustomerFeedBack/{ticketId}/{feedback}")
public ResponseEntity<?> addCustomerFeedBack(@PathVariable("ticketId") long ticketId,@PathVariable("feedback") int feedback){
		
		try{
			Ticket t=ticketService.findTicketById(ticketId);
			if(t!=null) {
				t.setRating(feedback);
				agentService.updateRating(t.getAgent(), feedback);
				ticketService.updateTicketStatus(t, "Rated");
				return new ResponseEntity<Ticket>(t, HttpStatus.CREATED);}
			else
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	    }
	    catch (Exception e){
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	@PutMapping("/reOpenTicket/{ticketid}")
	public ResponseEntity<?> reOpenTicket(@PathVariable("ticketid") long ticketId) {
		try{
			Ticket t=ticketService.findTicketById(ticketId);
			if(t!=null) {
				if(!(t.getStatus().equals("Closed")) ) {
					 return new ResponseEntity<String>("Ticket is not closed yet", HttpStatus.NOT_FOUND);
				}
				else {
					ticketService.updateTicketStatus(t, "Open");
					t.getCommunicationLogs().add("re");
		    		t.setCommunicationLogs(t.getCommunicationLogs());
					return new ResponseEntity<Ticket>(t, HttpStatus.OK);
				}
			}
			else
				return new ResponseEntity<String>("Ticket Not found", HttpStatus.NOT_FOUND);
	    }
	    catch (Exception e){
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	@GetMapping("/getTicket/{ticketId}")
	public Ticket getTicket(@PathVariable("ticketId") long ticketId){
		
	    	Ticket tl=ticketService.findTicketById(ticketId);
	    	return tl;
	}
	@PutMapping("/updateTicket")
	public ResponseEntity<?> updateTicket(@RequestBody Ticket ticket){
	    		
	    		ticketService.updateTicket(ticket);
	    		return new ResponseEntity<Ticket>(ticket,HttpStatus.OK);
	    		
	}
	@GetMapping("/getForwardedTicketsOfCust/{customerId}")
	public ResponseEntity<?> getForwardedTicketsOfCust(@PathVariable("customerId") long customerid){
		try{
	        return new ResponseEntity<List<Ticket>>(ticketService.getTicketsOfCustByStatus(customerid,"Reviewed"), HttpStatus.OK);
	    }
	    catch (Exception e){
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	   
	}
	@PutMapping("/forwardToAgent/{ticketid}/{comments}")
	public ResponseEntity<?> forwardToAgent(@PathVariable("ticketid") long ticketId,@PathVariable("comments") String comments) {
		try{
			Ticket t=ticketService.findTicketById(ticketId);
			if(t!=null) {
				System.out.println(t.getStatus());
				if(t.getStatus().equals("Reviewed")) {
					ticketService.updateTicketStatus(t, "Review");
					t.getCommunicationLogs().add(comments);
					t.getCommunicationLogs().add("f");
		    		t.setCommunicationLogs(t.getCommunicationLogs());
		    		ticketService.updateTicket(t);
					return new ResponseEntity<Ticket>(t, HttpStatus.OK);
					 
				}
				else {
					return new ResponseEntity<String>("No Response yet", HttpStatus.NOT_FOUND);
				}
			}
			else
				return new ResponseEntity<String>("Ticket Not found", HttpStatus.NOT_FOUND);
	    }
	    catch (Exception e){
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
}
