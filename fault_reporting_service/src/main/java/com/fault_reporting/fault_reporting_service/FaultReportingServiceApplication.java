package com.fault_reporting.fault_reporting_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FaultReportingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FaultReportingServiceApplication.class, args);
	}

}
