package com.Customer_UI.Customer_UI.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.Customer_UI.Customer_UI.model.Ticket;



@FeignClient("FAULT-REPORTING-SERVICE")
public interface FaultReportingFeignClient {

	@RequestMapping(method = RequestMethod.POST, value = "createTicket", consumes = "application/json")
	boolean createTicket(@RequestBody Ticket ticket);
}