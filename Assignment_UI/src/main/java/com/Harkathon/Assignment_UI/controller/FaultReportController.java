package com.Harkathon.Assignment_UI.controller;

import com.Harkathon.Assignment_UI.model.FaultReport;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FaultReportController {

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // Populate data for statistics cards
        model.addAttribute("highSeverityCount", 10);
        model.addAttribute("mediumSeverityCount", 5);
        // Add more attributes as needed

        // Populate data for recent fault reports
        List<FaultReport> recentReports = getRecentReports(); // Implement this method to fetch recent fault reports
        model.addAttribute("recentReports", recentReports);

        return "dashboard"; // This should match the name of your Thymeleaf template
    }

    private List<FaultReport> getRecentReports() {
        // Implement this method to fetch and return recent fault reports from your data source
        // For this example, I'll create dummy data
        List<FaultReport> reports = new ArrayList<>();
        reports.add(new FaultReport(1L, "Network outage", "HIGH", "John Doe"));
        reports.add(new FaultReport(2L, "Call quality issues", "MEDIUM", "Jane Smith"));
        // Add more fault reports
        return reports;
    }
}