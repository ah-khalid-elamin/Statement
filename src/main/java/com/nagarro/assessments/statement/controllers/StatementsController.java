package com.nagarro.assessments.statement.controllers;

import java.util.Date;
import java.util.List;

import com.nagarro.assessments.statement.domains.Statement;
import com.nagarro.assessments.statement.exceptions.InvalidAccountException;
import com.nagarro.assessments.statement.services.impl.AccountStatementImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statements")
public class StatementsController {

	@Autowired
	private AccountStatementImpl accountStatmentService;

	@GetMapping()
	@PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
	public List<Statement> GetStatements(@RequestParam(required = false) Long accountId,
		@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
		@RequestParam(required = false)  @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate,
		@RequestParam(required = false)  Double lower,
		@RequestParam(required = false)  Double higher) throws InvalidAccountException {
		
		if(accountId == null) throw new InvalidAccountException("Invalid Account Id."); 
		System.out.println(accountId);
		return accountStatmentService.getStatementsByAccountId(accountId, fromDate, toDate, lower, higher);
	}
}
