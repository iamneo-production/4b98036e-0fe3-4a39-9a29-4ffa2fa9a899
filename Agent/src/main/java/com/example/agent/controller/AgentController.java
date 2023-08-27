package com.example.agent.controller;

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

import com.example.agent.entity.Agent;
import com.example.agent.service.AgentServiceImpl;

@RestController
@RequestMapping("/agent")
public class AgentController {
	@Autowired
	private AgentServiceImpl agentService;
	
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
	public ResponseEntity<?> updateRating(@RequestBody Agent a,@PathVariable("rating") int rating){
	    try{
	    	//Agent a=agentService.getAgent(agent.getAgentId());
	    	a.setRating(rating);
	    	agentService.updateAgent(a);
	        return new ResponseEntity<Agent>(a, HttpStatus.CREATED);
	    }
	    catch (Exception e){
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	}
	
	@GetMapping("/getAgent/{agentId}")
	public ResponseEntity<?> addAgent(@PathVariable("agentId") Long agentId){
	    try{
	    	Agent a=agentService.getAgent(agentId);
	    	if(a!=null)
	    		return new ResponseEntity<Agent>(a, HttpStatus.CREATED);
	    	else
	    		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	    }
	    catch (Exception e){
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }

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
	
	@GetMapping("/home")
	public ResponseEntity<String> home(){
			System.out.println("home");
	        return new ResponseEntity<String>("home", HttpStatus.OK);
	   
	}
}
