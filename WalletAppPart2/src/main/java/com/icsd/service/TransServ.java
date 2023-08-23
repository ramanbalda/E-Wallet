package com.icsd.service;



import com.icsd.dto.TransactionDepositDTO;
import com.icsd.model.Transaction;

public interface TransServ {

	public void SaveTrans(Transaction tr);
	 public Transaction withdrow(TransactionDepositDTO dt);
	 public Transaction Deposit(TransactionDepositDTO dt);
	 public Transaction Transf(TransactionDepositDTO dt);


}
