package com.icsd.serviceImp;

import java.io.File;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icsd.dto.request.CustomerRequestDto;
import com.icsd.exceptionhand.EntityAlreadyExistException;
import com.icsd.exceptionhand.ResourceNotFoundException;
import com.icsd.model.Address;
import com.icsd.model.Customer;
import com.icsd.model.CustomerDocuments;
import com.icsd.repo.Addressrepo;
import com.icsd.repo.Customerdocrepo;
import com.icsd.repo.Customerrepo;
import com.icsd.service.CustServ;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerServiceImp implements CustServ {

	@Autowired(required = false)
	Customerrepo customerRepo;
	@Autowired(required = false)
	Addressrepo addressRepo;
	

	@Override
	public Customer getCustomerByEmailid(String strEmailId) {
		log.info("Inside getCustomer by email is"+strEmailId);
	Optional<Customer> c=	customerRepo.findByEmailId(strEmailId);
	log.info("Got optional C from repo"+c);
		return c.get();
	}

	@SuppressWarnings("deprecation")
	@Override
	public Customer getCustomerByCustId(int strCustId) {
		log.info("Inside getCustomer by custid is"+strCustId);
		return customerRepo.getById(strCustId);
	}

	@Override
	public Integer createCustomer(@Valid CustomerRequestDto crDto) {
		log.info("Inside save customer of service with given request"+crDto);
		 Optional<Customer> optCust=customerRepo.findByEmailId(crDto.getEmailId());
		  if(optCust.isPresent())
		  {
		   throw new EntityAlreadyExistException("Customer email id is already existing ");
		  }
		  Address add=Address.builder().addressLine1(crDto.getAddressLine1())
		    .addressLine2(crDto.getAddressLine2())
		    .city(crDto.getCity())
		    .state(crDto.getState())
		    .pincode(crDto.getPincode())
		    .build();
		 
		  Address addCreated=addressRepo.save(add);
		  
		  
		  Customer cust=Customer.builder().firstName(crDto.getFirstName()).lastName(crDto.getLastName()).emailId(crDto.getEmailId())
		    .contactNo(crDto.getContactNo()).address(addCreated).gender(crDto.getGender()).password(crDto.getPassword()).registerationDate(LocalDate.now())
		    
		    .build();
		  System.out.println("cust entity is now from builder : "+ cust);
		  log.info("Cust enttity saved",cust);;
		  Customer custCreated=customerRepo.save(cust);
		  
		  
		  
		  return custCreated.getCustomerId();
		
		 }


	

	@Override
	public Customer registerCustomer(Customer customer) {
		 Customer custCreated=customerRepo.save(customer);
		return custCreated;
	}

	@Override
	public boolean validateLogin(String email, String password) {
		Optional<Customer> c=customerRepo.findByEmailIdAndPassword(email, password);
		Customer cud=c.get();
		if(cud!=null) {
			return true;
		}
		
		
		return false;
	}

	@Override
	public Customer getcustbyemlandpwd(String email, String password) {
		log.info("Inside validbyemailandpwd  of"+email+password);
		Optional<Customer> c=customerRepo.findByEmailIdAndPassword(email, password);
			
		Customer cud=c.get();
			if(cud==null) {
			throw new ResourceNotFoundException("Customer not found of such email,pwd");
			}
			
			
			return cud;
		
	
	}

	
}
	
	

