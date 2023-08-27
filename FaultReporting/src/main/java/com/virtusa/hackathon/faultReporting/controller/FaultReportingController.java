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
import com.virtusa.hackathon.faultReporting.service.CustomerServiceImpl;
import com.virtusa.hackathon.faultReporting.service.TicketServiceImpl;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/faultReporting")
public class FaultReportingController {
	@Autowired
	private CustomerServiceImpl customerService;
	@Autowired
	private TicketServiceImpl ticketService;
	
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
	
	
}
