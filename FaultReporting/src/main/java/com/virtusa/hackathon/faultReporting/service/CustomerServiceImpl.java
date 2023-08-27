package com.virtusa.hackathon.faultReporting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.hackathon.faultReporting.entity.Customer;
import com.virtusa.hackathon.faultReporting.repository.CustomerRepo;
@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
    private CustomerRepo customerRepository;

 

 

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

 

    public String addCustomer(Customer customer){
        if(!customerRepository.existsByCustomerEmail(customer.getCustomerEmail())){
            customerRepository.save(customer);
            return "Customer added successfully";
        }
        else {
            return "Customer already added";
        }
    }

 

    

 

 

    public void updateCustomer(Customer customer){
        customerRepository.save(customer);
    }

 

    public Customer findByEmail(String customerEmail){
        return customerRepository.findByCustomerEmail(customerEmail);
    }
    public Customer login(String email,String password) {
    	Customer c=customerRepository.findByCustomerEmail(email);
    	if(c!=null) {
    		if(c.getCustomerPassword().equals(password)) {
    			return c;
    		}
    	}
    	return null;
    }
    
    public Customer getCustomer(long custId) {
    	return customerRepository.findByCustomerId(custId);
    }
 
}
