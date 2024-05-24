package com.app.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.entity.Account;
import com.app.repository.AccountRepo;
import com.app.service.AccountService;

public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepo accountRepo;

	@Override
	public List<Account> findAllAccount() {
		return accountRepo.findAll();
	}

	@Override
	public Optional<Account> getAccount(Long id) {
		return accountRepo.findById(id);
	}

	@Override
	public Account createAccount(Account account) {
		return accountRepo.save(account);
	}

	@Override
	public String deleteAccountById(Long id) {
		accountRepo.deleteById(id);
		return "Account for id : " + id + " deleted successfully !";
	}

	@Override
	public Account deposit(Long id, double amount) {

		Account account = getAccount(id).orElseThrow(() -> new RuntimeException("Account not found"));
		account.setBalance(account.getBalance() + amount);
		return accountRepo.save(account);

	}

	@Override
	public Account withdraw(Long id, double amount) {

		Account account = getAccount(id).orElseThrow(() -> new RuntimeException("Account not found"));
		if (account.getBalance() < amount) {
			throw new RuntimeException("Insufficient funds");
		}
		account.setBalance(account.getBalance() - amount);
		return accountRepo.save(account);
	}

}
