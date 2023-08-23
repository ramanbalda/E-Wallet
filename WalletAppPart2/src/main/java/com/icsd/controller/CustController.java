package com.icsd.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icsd.apiresponce.ApiResponse;
import com.icsd.dto.CustomerLoginDTO;
import com.icsd.dto.request.CustomerRequestDto;
import com.icsd.model.Customer;
import com.icsd.service.AddServ;
import com.icsd.service.CustServ;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping(value = "/customer")
@Slf4j
public class CustController {

	
	@Autowired(required = false)
	CustServ cs;
	@Autowired(required = false)
	AddServ add;
	
	 @PostMapping(value = "/create")
	 public ResponseEntity<ApiResponse> createCustomer(@RequestBody @Valid CustomerRequestDto customerRequest) 
	 {
	  log.info("inside create method of cusgtomer controller");
		  Integer generatedCustomerId=cs.createCustomer(customerRequest);
	   ApiResponse apiResponse = new ApiResponse(HttpStatus.OK.value(), "Customer Created Successfully",  generatedCustomerId);
	  return new ResponseEntity<ApiResponse> (apiResponse,HttpStatus.OK);
	  
	 }
	 
	 
	 @GetMapping("/findByEmail/{emailId}")
		public ResponseEntity<Customer> getCustomerByEmailId(@PathVariable String emailId) {
			
			Customer customer=cs.getCustomerByEmailid(emailId);
			 if (customer == null) {
			        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			    } else {
			        return new ResponseEntity<>(customer, HttpStatus.OK);
			    }
			 
		}
		@GetMapping("/findById/{customerId}")
		public ResponseEntity<Customer> getCustomerByCustomerId(@PathVariable int customerId) {

			Customer customer=cs.getCustomerByCustId(customerId);
			 if (customer == null) {
			        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			    } else {
			        return new ResponseEntity<>(customer, HttpStatus.OK);
			    }
		}
		 
		@PostMapping("/findByemailandpwd")
		public ResponseEntity<Customer> getCustomerByEmailAndPwd(@RequestBody CustomerLoginDTO clog) {
			

			Customer customer=cs.getcustbyemlandpwd(clog.getEmailId(),clog.getPwd());
			 if (customer == null) {
			        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			    } else {
			        return new ResponseEntity<>(customer, HttpStatus.OK);
			    }
		}
	
	 

	
	
	
	
	
	
	
	
}
