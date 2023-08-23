package com.icsd.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.icsd.apiresponce.ApiResponse;
import com.icsd.exceptionhand.ResourceNotFoundException;
import com.icsd.model.Customer;
import com.icsd.model.CustomerDocuments;
import com.icsd.service.CustDocServ;
import com.icsd.service.CustServ;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/custdoc")
public class DocController {
	@Autowired
	CustServ cs;
	@Autowired
	CustDocServ csds;

	@PostMapping("/uploaddocument/{id}")
	public ResponseEntity<ApiResponse> uploaddoc(@PathVariable int id, @RequestParam("FileType") String FileType,
			@RequestParam("file") MultipartFile file) {
		Customer customer = cs.getCustomerByCustId(id);
		if (customer == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			int retid=csds.savedoc(customer, FileType, file);
			ApiResponse apiResponse = new ApiResponse(HttpStatus.OK.value(), "File Uploaded Successfully", retid);
			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
		}
	}
	
	@GetMapping("/getdocument/{id}")
	public ResponseEntity<Resource> getfilebydocid(@PathVariable int id) throws MalformedURLException {
		CustomerDocuments custdoc=csds.fromcustdocid(id);
		File f=new File(custdoc.getUploadedDocumentspath());
		
        UrlResource resource = new UrlResource(f.toURI());
        Resource res;
        if(resource.exists()) {
             res= (Resource) resource;
        } else {
            throw new ResourceNotFoundException("File not found " + custdoc.getUploadedDocumentspath());
        }
		 return ResponseEntity.ok()
				 .contentType(MediaType.IMAGE_JPEG)
	                .body(res);
	}
	
	

}
