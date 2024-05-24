package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.entity.Account;

public interface AccountService {

	public List<Account> findAllAccount();
	
	public Optional<Account> getAccount(Long id) ;
	
	public Account createAccount(Account account);
	
	public String deleteAccountById(Long id);
	
	public Account deposit(Long id , double amount);
	
	public Account withdraw(Long id , double amount);

	
}
