package com.virtusa.hackathon.faultAssignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.virtusa.hackathon.faultAssignment.entity.Customer;



public interface CustomerRepo extends JpaRepository<Customer, Long>{

}
