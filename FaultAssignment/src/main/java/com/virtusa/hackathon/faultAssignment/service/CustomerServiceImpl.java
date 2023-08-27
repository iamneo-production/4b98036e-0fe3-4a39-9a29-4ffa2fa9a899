package com.virtusa.hackathon.faultAssignment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.hackathon.faultAssignment.entity.Customer;
import com.virtusa.hackathon.faultAssignment.repository.CustomerRepo;


@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
    private CustomerRepo customerRepository;
    
 
}
