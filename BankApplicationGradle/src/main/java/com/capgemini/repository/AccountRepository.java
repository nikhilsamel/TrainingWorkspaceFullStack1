package com.capgemini.repository;

import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.InsufficientInitialAmountException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.model.Account;

public interface AccountRepository {
	
	boolean save(Account account) throws InsufficientInitialAmountException;
	
	Account searchAccount(int accountNumber) throws InvalidAccountNumberException;
	
	Boolean depositAccount(int accountNumber, int amount) throws InvalidAccountNumberException;
	
	Boolean withdrawAccount(int accountNumber, int amount) throws InvalidAccountNumberException, InsufficientBalanceException;

}
