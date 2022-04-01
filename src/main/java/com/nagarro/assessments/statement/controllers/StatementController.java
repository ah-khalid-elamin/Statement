package com.nagarro.assessments.statement.controllers;

import java.util.Date;
import java.util.List;

import com.nagarro.assessments.statement.domains.Statement;
import com.nagarro.assessments.statement.services.AccountStatmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statements")
public class StatementController {

	@Autowired
	private AccountStatmentService accountStatmentService;

//	@GetMapping()
//	public List<Statement> GetAccountStatement(
//			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
//			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate,
//			@RequestParam Double lower,
//			@RequestParam Double upper) {
//		return accountStatmentService.GetStatementsBy(fromDate, toDate, lower, upper);
//	}
	@GetMapping()
	public List<Statement> GetAccountStatements() {
		return accountStatmentService.GetStatements();
	}
}
