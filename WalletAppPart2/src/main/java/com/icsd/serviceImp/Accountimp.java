package com.icsd.serviceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icsd.dto.request.AccountRequestDto;
import com.icsd.exceptionhand.EntityAlreadyExistException;
import com.icsd.exceptionhand.ResourceNotFoundException;
import com.icsd.model.Account;
import com.icsd.model.AccountType;
import com.icsd.model.Customer;
import com.icsd.repo.AccountRepo;
import com.icsd.service.AccServ;
import com.icsd.service.CustServ;

import lombok.extern.slf4j.Slf4j;
@Service 
@Slf4j
public class Accountimp implements AccServ {

	@Autowired
	AccountRepo accr;
	@Autowired
	CustServ cserv;
	
	@Override
	public Account addAccount( AccountRequestDto account) {
		log.info("Inside addaccount");

	Optional<Account> ac=accr.findByCustomerCustomerIdAndAccountType(account.getCustomerid(), account.getAccountType());
	if(ac.isPresent())
	{
	   throw new EntityAlreadyExistException("Your Account of such type is already exist");
		  }
	
		Customer cust=cserv.getCustomerByCustId(account.getCustomerid());
	
		Account acou=Account.builder().
				accountType(account.getAccountType())
				.customer(cust)
				.description(account.getDescription())
				.openingBalance(account.getOpeningBalance())
				.openingDate(account.getOpeningDate()).build();
		
		log.info("Account Build Successfully"+acou);
		accr.save(acou);
		log.info("Account Saved SuccessFully");
		return acou;
	}

	@Override
	public Account findAccountbyAccountno(int accountNumber) {
		log.info("Inside Account by accountno");
	Optional<Account> acco=	accr.findById(accountNumber);
		
		if(acco.isEmpty()) {
			throw new ResourceNotFoundException("You Don't Have any Account of such Account number");
		}
		log.info("account found Successfully");
		Account acc= acco.get();
		log.info("Get object of Account from optional");
		
		return acc;
	}

	@Override
	public List<Account> getAllAccounts() {
		List<Account> lst=accr.findAll();
		return lst;
	}

	@Override
	public List<Account> getAllAccountsByCustomerid(int customer) {
		
		List<Account> acclst=accr.findByCustomerCustomerId(customer);
		if(acclst.isEmpty()) {
			throw new ResourceNotFoundException("You Don't Have any Account Yet create one");
		}
		
		
		return acclst;
	}

	@Override
	public int deleteAccount(int accountNumber) {
		
		Account ac=findAccountbyAccountno(accountNumber);
		
		accr.deleteById(ac.getAccountNumber());
		log.info("Account Deleted Successfully");
		
		return accountNumber;
	}

	@Override
	public Account saveAccount(Account acc) {
		
		accr.save(acc);
		
		
		return acc;
	}


}
