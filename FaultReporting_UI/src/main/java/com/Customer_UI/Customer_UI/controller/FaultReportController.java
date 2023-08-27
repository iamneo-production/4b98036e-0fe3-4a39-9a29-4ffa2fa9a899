package com.Customer_UI.Customer_UI.controller;



import com.Customer_UI.Customer_UI.entity.FaultReport;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FaultReportController {

    @GetMapping("/fault-reports")
    public String showFaultReportForm(Model model) {
        model.addAttribute("faultReport", new FaultReport());
        return "fault-report-form";
    }

    @PostMapping("/fault-reports")
    public String submitFaultReport(FaultReport faultReport) {
        // Logic to process the submitted fault report
        return "redirect:/fault-reports"; // Redirect to the form page again
    }
}
