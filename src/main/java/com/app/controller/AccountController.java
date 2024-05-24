package com.app.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Account;
import com.app.service.AccountService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/bank")
@RequiredArgsConstructor
public class AccountController {

	private final AccountService accountService;
	
	
	@GetMapping
	public ResponseEntity<List<Account>> getAllAcaount(){
		return ResponseEntity.ok(accountService.findAllAccount()) ;
	}
	
	
	@GetMapping("/{id}")
	public Optional<Account> getAccountByID(@PathVariable  Long id){
		return accountService.getAccount(id);
	}
	
	@PostMapping("/user")
	public ResponseEntity<Account> createUser(@RequestBody Account account){
		return ResponseEntity.ok(accountService.createAccount(account));
	}
	
	
	@DeleteMapping("/{id}")
	public String deleteById(@PathVariable Long id) {
		return accountService.deleteAccountById(id);
	}
	
	
	@PostMapping("/{id}/deposit")
	public ResponseEntity<Account> deposit( @PathVariable Long id ,@RequestBody Map<String, Double> request){
		double amount = request.get("amount");
		return ResponseEntity.ok(accountService.deposit(id, amount));
	}
	
	
	@PostMapping("/{id}/withdraw")
	public ResponseEntity<Account> withdraw(@PathVariable Long id ,@RequestBody Map<String, Double> request){
		double amount = request.get("amount");
		return ResponseEntity.ok(accountService.withdraw(id, amount));
	}
	
	
	
	
}
