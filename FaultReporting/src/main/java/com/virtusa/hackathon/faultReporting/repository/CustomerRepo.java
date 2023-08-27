package com.virtusa.hackathon.faultReporting.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.virtusa.hackathon.faultReporting.entity.Customer;


public interface CustomerRepo extends JpaRepository<Customer, Long>{
	boolean existsByCustomerEmail(String customerEmail);
	Customer findByCustomerEmail(String customerEmail);
	Customer findByCustomerId(long custId);

}
