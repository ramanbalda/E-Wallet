package com.icsd.serviceImp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icsd.model.Address;
import com.icsd.repo.Addressrepo;


@Service
public class AddImp implements com.icsd.service.AddServ{

	@Autowired
	Addressrepo addre;
	
	@Override
	public Address addAddress(Address add) {
		addre.save(add);
		return add;
	}

	@Override
	public Address getAddressbyAddid(int addid) {

	Optional<Address>addopt=addre.findById(addid);
		Address add= addopt.get();
		
		return add;
	}
}
