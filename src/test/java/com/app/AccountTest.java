package com.app;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.app.controller.AccountController;
import com.app.entity.Account;
import com.app.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
public class AccountTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AccountService accountService;

	@InjectMocks
	private AccountController accountController;

	private ObjectMapper objectMapper = new ObjectMapper();

	Account acc1 = new Account(1L, "mohamed", 200.0);
	Account acc2 = new Account(2L, "ahmed", 500.0);
	Account acc3 = new Account(3L, "sasa", 800.0);

	@Test
	public void getAllRecords_success() throws Exception {

		List<Account> records = new ArrayList<>(Arrays.asList(acc1, acc2, acc3));

		Mockito.when(accountService.findAllAccount()).thenReturn(records);

		mockMvc.perform(MockMvcRequestBuilders
				.get("/bank").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[0].accountHolderName", is("mohamed")))
				.andExpect(jsonPath("$[1].accountHolderName", is("ahmed")))
				.andExpect(jsonPath("$[2].accountHolderName", is("sasa")));
	}

	@Test
	public void getAccountById_success() throws Exception {

		Mockito.when(accountService.getAccount(acc1.getId())).thenReturn(Optional.of(acc1));

		mockMvc.perform(MockMvcRequestBuilders
				.get("/bank/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$.accountHolderName", is("mohamed")));
	}
	

	@Test
	public void createNewAccount() throws Exception {

		Account acc4 = new Account(4L, "ramy", 500.0);

		Mockito.when(accountService.createAccount(Mockito.any(Account.class))).thenReturn(acc4);

		mockMvc.perform(MockMvcRequestBuilders
				.post("/bank/user").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(acc4)))
		        .andExpect(status().isOk())
				.andExpect(jsonPath("$.accountHolderName", is("ramy")))
				.andExpect(jsonPath("$.balance", is(500.0)));
	}

	
	@Test
	public void deleteById_success() throws Exception {
		
		Long accountId = 1L;

		Mockito.when(accountService.deleteAccountById(accountId)).thenReturn("Account deleted successfully");

		mockMvc.perform(MockMvcRequestBuilders
				.delete("/bank/" + accountId))
		        .andExpect(status().isOk())
				.andExpect(jsonPath("$", is("Account deleted successfully")));
	}
	

	
    @Test
    public void deposit_success() throws Exception {
        Long accountId = 1L;
        double amount = 100.0;
        Account updatedAccount = new Account(accountId, "mohamed", 300.0);
        Map<String, Double> request = new HashMap<>();
        request.put("amount", amount);

        Mockito.when(accountService.deposit(accountId, amount)).thenReturn(updatedAccount);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/bank/" + accountId + "/deposit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountHolderName", is("mohamed")))
                .andExpect(jsonPath("$.balance", is(300.0)));
    }

    
    @Test
    public void withdraw_success() throws Exception {
        Long accountId = 1L;
        double amount = 50.0;
        Account updatedAccount = new Account(accountId, "mohamed", 150.0);
        Map<String, Double> request = new HashMap<>();
        request.put("amount", amount);

        Mockito.when(accountService.withdraw(accountId, amount)).thenReturn(updatedAccount);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/bank/" + accountId + "/withdraw")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountHolderName", is("mohamed")))
                .andExpect(jsonPath("$.balance", is(150.0)));
    }

}
