package com.capgemini.service;

import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.InsufficientInitialAmountException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.model.Account;
import com.capgemini.repository.AccountRepository;

public class AccountServiceImpl implements AccountService {
	
	/* (non-Javadoc)
	 * @see com.capgemini.service.AccountService#createAccount(int, int)
	 */
	
	AccountRepository accountRepository;
	
	
	public AccountServiceImpl(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}


	@Override
	public Account createAccount(int accountNumber,int amount) throws InsufficientInitialAmountException
	{
		Account account = new Account();
		account.setAccountNumber(accountNumber);
		account.setAmount(amount);
		
		if(amount<5000)
		{
			throw new InsufficientInitialAmountException();
		}
		 
		if(accountRepository.save(account))
		{
			return account;
		}
	     
		return null;
		
	}
	
	@Override
	public Account searchAccount(int accountNumber) throws InvalidAccountNumberException
	{
		Account account = new Account();
		account = accountRepository.searchAccount(accountNumber);
	     
		return account;
		
	}
	
	@Override
	public Boolean depositAccount(int accountNumber, int amount) throws InvalidAccountNumberException
	{
		Account account = new Account();
		account = accountRepository.searchAccount(accountNumber);
		Boolean depositFlag = accountRepository.depositAccount(accountNumber, amount);
	     
		return depositFlag;
		
	}
	
	@Override
	public Boolean withdrawAccount(int accountNumber, int amount) throws InvalidAccountNumberException, InsufficientBalanceException
	{
		Account account = new Account();
		account = accountRepository.searchAccount(accountNumber);
		Boolean withdrawFlag = accountRepository.withdrawAccount(accountNumber, amount);
	     
		return withdrawFlag;
		
	}

}
