package com.icsd.service;

import org.springframework.web.multipart.MultipartFile;

import com.icsd.model.Customer;
import com.icsd.model.CustomerDocuments;

public interface CustDocServ {

	public int savedoc(Customer cstmer,String filetype,MultipartFile file);
	public CustomerDocuments savecustdoc(CustomerDocuments cd);
	public CustomerDocuments fromcustdocid(int custdocid);
	
}
