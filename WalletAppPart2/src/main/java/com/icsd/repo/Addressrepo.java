package com.icsd.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.icsd.model.Address;

public interface Addressrepo  extends JpaRepository<Address, Integer>{

	
}
