package com.nagarro.assessments.statement.controllers;

import java.util.List;

import com.nagarro.assessments.statement.domains.Account;
import com.nagarro.assessments.statement.services.impl.AccountStatementImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
public class AccountsController {
    @Autowired
	private AccountStatementImpl accountStatmentService;


	@GetMapping()
	public List<Account> GetAccounts() {
		return accountStatmentService.getAccounts();
	}
}
