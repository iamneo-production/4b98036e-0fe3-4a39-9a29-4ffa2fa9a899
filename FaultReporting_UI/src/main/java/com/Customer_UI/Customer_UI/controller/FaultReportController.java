package com.Customer_UI.Customer_UI.controller;



import com.Customer_UI.Customer_UI.entity.FaultReport;
import com.Customer_UI.Customer_UI.model.Ticket;
import com.Customer_UI.Customer_UI.service.client.FaultReportingFeignClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class FaultReportController {

	@Autowired
	private FaultReportingFeignClient faultReportingFeignClient;
	
    @GetMapping("/fault-reports")
    public String showFaultReportForm(Model model) {
        model.addAttribute("faultReport", new FaultReport());
        return "fault-report-form";
    }

    @PostMapping("/createTicket")
    @RequestMapping(
    		  path = "/createTicket", 
    		  method = RequestMethod.POST,
    		  consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, 
    		  produces = {
    		    MediaType.APPLICATION_ATOM_XML_VALUE, 
    		    MediaType.APPLICATION_JSON_VALUE
    		  })
    public String submitFaultReport(FaultReport faultReport) {
        // Logic to process the submitted fault report
    	Ticket ticket= new Ticket();
    	ticket.setDescription(faultReport.getDescription());
    	ticket.setPriority(faultReport.getSeverity());
    	faultReportingFeignClient.createTicket(ticket);
       return "redirect:/fault-reports"; // Redirect to the form page again
    }
}
