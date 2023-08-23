package com.icsd.service;

import com.icsd.model.Address;

public interface AddServ {
	
	public Address addAddress(Address add);
	public Address getAddressbyAddid(int custid);
	
}
