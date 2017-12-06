package com.capgemini.test;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.InsufficientInitialAmountException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.model.Account;
import com.capgemini.repository.AccountRepository;
import com.capgemini.service.AccountService;
import com.capgemini.service.AccountServiceImpl;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
public class AccountTest {

	AccountService accountService;
	
	@Mock
	AccountRepository accountRepository;
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		accountService = new AccountServiceImpl(accountRepository);
	}

	/*
	 * create account
	 * 1.when the amount is less than 500 then system should throw exception
	 * 2.when the valid info is passed account should be created successfully
	 */
	
	@Test(expected=com.capgemini.exceptions.InsufficientInitialAmountException.class)
	public void whenTheAmountIsLessThan500SystemShouldThrowException() throws InsufficientInitialAmountException
	{
		/*Account account =new Account();
		account.setAccountNumber(101);
		account.setAmount(400);
		when(accountRepository.save(account)).thenThrow(new InsufficientInitialAmountException());*/
		accountService.createAccount(101, 400);
	}
	
	@Test
	public void whenTheValidInfoIsPassedAccountShouldBeCreatedSuccessfully() throws InsufficientInitialAmountException
	{
		Account account =new Account();
		account.setAccountNumber(101);
		account.setAmount(5000);
		when(accountRepository.save(account)).thenReturn(true);
		assertEquals(account, accountService.createAccount(101, 5000));
	}
	
	/*
	 * deposit account
	 * 1.when the account number is invalid then system should throw exception
	 * 2.when the valid info is passed deposit to account should be created successfully
	 */
	
	@Test(expected=com.capgemini.exceptions.InvalidAccountNumberException.class)
	public void whenTheAccountNumberIsInvalidForDepositSystemShouldThrowException() throws InvalidAccountNumberException
	{
		Account account =new Account();
		account.setAccountNumber(103);
		account.setAmount(5000);
		when(accountRepository.depositAccount(103, 400)).thenThrow(new InvalidAccountNumberException());
		accountService.depositAccount(103, 400);
	}
	
	@Test
	public void whenTheValidInfoIsPassedDepositToAccountShouldBeCreatedSuccessfully() throws InvalidAccountNumberException
	{
		Account account =new Account();
		account.setAccountNumber(101);
		account.setAmount(5000);
		when(accountRepository.depositAccount(101, 400)).thenReturn(true);
		assertEquals(true, accountService.depositAccount(101, 400));
	}
	
	/*
	 * withdraw account
	 * 1.when the account number is invalid then system should throw exception
	 * 2.when the balance is insufficient then system should throw exception
	 * 3.when the valid info is passed withdraw from account should be created successfully
	 */
	
	@Test(expected=com.capgemini.exceptions.InvalidAccountNumberException.class)
	public void whenTheAccountNumberIsInvalidForWithdrawSystemShouldThrowException() throws InvalidAccountNumberException, InsufficientBalanceException
	{
		Account account =new Account();
		account.setAccountNumber(103);
		account.setAmount(400);
		when(accountRepository.withdrawAccount(103, 400)).thenThrow(new InvalidAccountNumberException());
		accountService.withdrawAccount(103, 400);
	}
	
	@Test(expected=com.capgemini.exceptions.InsufficientBalanceException.class)
	public void whenTheBalanceIsInsufficientForWithdrawSystemShouldThrowException() throws InvalidAccountNumberException, InsufficientBalanceException
	{
		Account account =new Account();
		account.setAccountNumber(101);
		account.setAmount(5000);
		when(accountRepository.withdrawAccount(101, 400)).thenThrow(new InsufficientBalanceException());
		accountService.withdrawAccount(101, 400);
	}
	
	@Test
	public void whenTheValidInfoIsPassedWithdrawFromAccountShouldBeCreatedSuccessfully() throws InvalidAccountNumberException, InsufficientBalanceException
	{
		Account account =new Account();
		account.setAccountNumber(101);
		account.setAmount(5000);
		when(accountRepository.withdrawAccount(101, 400)).thenReturn(true);
		assertEquals(true, accountService.withdrawAccount(101, 400));
	}

}
