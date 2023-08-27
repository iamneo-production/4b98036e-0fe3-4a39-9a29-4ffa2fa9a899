package com.Customer_UI.Customer_UI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScans({ @ComponentScan("com.Customer_UI.Customer_UI") })
@EnableFeignClients("com.Customer_UI.Customer_UI.service.client")
public class CustomerUiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerUiApplication.class, args);
	}

}
