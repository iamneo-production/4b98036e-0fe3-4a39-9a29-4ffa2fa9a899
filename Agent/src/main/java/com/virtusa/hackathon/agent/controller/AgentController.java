package com.virtusa.hackathon.agent.controller;

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

import com.virtusa.hackathon.agent.dto.Ticketdto;
import com.virtusa.hackathon.agent.entity.Agent;
import com.virtusa.hackathon.agent.service.AgentServiceImpl;
import com.virtusa.hackathon.agent.service.FaultAService;
import com.virtusa.hackathon.agent.service.FaultRServiceProxy;

@RestController
@RequestMapping("/agent")
public class AgentController {
	@Autowired
	private AgentServiceImpl agentService;
	@Autowired
	private FaultRServiceProxy ticketService;
	@Autowired
	private FaultAService tService;
	@PostMapping("/addAgent")
	public ResponseEntity<?> addAgent(@RequestBody Agent agent){
	    try{
	    	agent.setWorkload(0);
	    	agent.setAvailability(true);
	    	agentService.addAgent(agent);
	        return new ResponseEntity<Agent>(agent, HttpStatus.CREATED);
	    }
	    catch (Exception e){
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	}
	
	@PutMapping("/updateRating/{rating}")
	public ResponseEntity<?> updateRating(@RequestBody Agent a,@PathVariable("rating") float rating){
	    try{
	    	int n=tService.allTicketsOfagent(a.getAgentId()).size();
	    	agentService.updateAgentRating(n,rating,a);
	        return new ResponseEntity<Agent>(a, HttpStatus.CREATED);
	    }
	    catch (Exception e){
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	}
	
	@GetMapping("/getAgent/{agentId}")
	public Agent getAgent(@PathVariable("agentId") Long agentId){
	   
	    	Agent a=agentService.getAgent(agentId);
	    	if(a!=null)
	    		return a;
	    	else
	    		return null;
	
	   

	}
	
	@GetMapping("/getAvailability/{agentId}")
	public ResponseEntity<?> getAvailability(@PathVariable("agentId") long agentId){
	    try{
	    	Agent a=agentService.getAgent(agentId);
	    	if(a.getAvailability().booleanValue())
	    		return new ResponseEntity<>(true, HttpStatus.OK);
	    	else
	    		return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
	    }
	    catch (Exception e){
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	}
	
	@GetMapping("/availableAgents")
	public ResponseEntity<?> availableAgents(){
	    try{
	    	
	        return new ResponseEntity<List<Agent>>(agentService.getAvailabileAgents(), HttpStatus.OK);
	    }
	    catch (Exception e){
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	}
	
	@GetMapping("/agentsByRating")
	public List<Agent> agentsByRating(){
	   
	    	
	        return agentService.getAgentsOrderRating();
	    
	}
	
	@RequestMapping("/updateAgentAvailability")
	public void updateAgentAvailability(@RequestBody Agent a ){
			
	    	agentService.updateAvailability(a);
	    
	}
	@PutMapping("/updateAgent")
	public ResponseEntity<?> updateAgent(@RequestBody Agent a ){
		agentService.updateAgent(a);
		try{
	    	
	        return new ResponseEntity<Agent>(a, HttpStatus.OK);
	    }
	    catch (Exception e){
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    	
	    
	}
	@GetMapping("getActiveTickets/{agentId}")
	public ResponseEntity<?> getActiveTickets(@PathVariable("agentId") long agentId){
		 try{
				if(tService.allOpenTicketsOfagent(agentId)!=null) 
		    		return new ResponseEntity<List<Ticketdto>>(tService.allOpenTicketsOfagent(agentId), HttpStatus.OK);
		    	else
		    		return new ResponseEntity<String>("Agent not found", HttpStatus.NOT_FOUND);
		    }
		    catch (Exception e){
		        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		    }

	}
	@PutMapping("/reviewTicket/{ticketId}")
	public ResponseEntity<?> reviewTicket(@PathVariable("ticketId") long ticketId){
	    try{
	    	Ticketdto t=ticketService.getTicket(ticketId);
	    	if(t!=null) {
	    		    		t.setStatus("Checking");
	    			    	t.getCommunicationLogs().add("R");
	    		    		t.setCommunicationLogs(t.getCommunicationLogs());
	    		    		ticketService.updateTicket(t);
	    		    		return new ResponseEntity<Ticketdto>(t, HttpStatus.OK);
	    		    	}
	    		
	    	else
	    		return new ResponseEntity<>("ticket not found", HttpStatus.NOT_FOUND);
	    }
	    catch (Exception e){
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }}
	@PutMapping("/forwardToCustomer/{ticketId}/{comments}")
	public ResponseEntity<?> forwardToCustomer(@PathVariable("ticketId") long ticketId,@PathVariable("comments") String comments){
	    try{
	    	Ticketdto t=ticketService.getTicket(ticketId);
	    	if(t!=null) {
	    		    		t.setStatus("Reviewed");
	    		    		t.getCommunicationLogs().add(comments);
	    			    	t.getCommunicationLogs().add("f");
	    		    		t.setCommunicationLogs(t.getCommunicationLogs());
	    		    		ticketService.updateTicket(t);
	    		    		return new ResponseEntity<Ticketdto>(t, HttpStatus.OK);
	    		    	}
	    		
	    	else
	    		return new ResponseEntity<>("ticket not found", HttpStatus.NOT_FOUND);
	    }
	    catch (Exception e){
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }}
	
	@PutMapping("/completeTicket/{ticketId}/{comments}")
	public ResponseEntity<?> completeTicket(@PathVariable("ticketId") long ticketId,@PathVariable("comments") String comments){
	    try{
	    	Ticketdto t=ticketService.getTicket(ticketId);
	    	if(t!=null) {
	    		    		t.setStatus("Completed");
	    		    		t.getCommunicationLogs().add(comments);
	    			    	t.getCommunicationLogs().add("C");
	    		    		t.setCommunicationLogs(t.getCommunicationLogs());
	    		    		ticketService.updateTicket(t);
	    		    		return new ResponseEntity<Ticketdto>(t, HttpStatus.OK);
	    		    	}
	    		
	    	else
	    		return new ResponseEntity<>("ticket not found", HttpStatus.NOT_FOUND);
	    }
	    catch (Exception e){
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }}
}
