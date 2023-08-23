package com.icsd.service;

import com.icsd.dto.request.CustomerRequestDto;

import com.icsd.model.Customer;


import jakarta.validation.Valid;

public interface CustServ {
	public Customer registerCustomer(Customer customer );
	public boolean validateLogin(String email,String password);

	public Customer getcustbyemlandpwd(String email,String password);
	public Integer createCustomer(@Valid CustomerRequestDto customerRequest);
	public Customer getCustomerByEmailid(String strEmailId);
	public Customer getCustomerByCustId(int strCustId);
	
}
