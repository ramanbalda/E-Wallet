package com.icsd.repo;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.icsd.model.Customer;

public interface Customerrepo  extends JpaRepository<Customer, Integer>{
	
	public Optional<Customer> findByEmailIdAndPassword(String email, String password);

	public Optional<Customer> findByEmailId(String emailId);
	

}
