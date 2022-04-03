package com.nagarro.assessments.statement.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.nagarro.assessments.statement.domains.Account;
import com.nagarro.assessments.statement.domains.Statement;
import com.nagarro.assessments.statement.repositories.AccountRepository;
import com.nagarro.assessments.statement.repositories.StatementRepository;
import com.nagarro.assessments.statement.services.AccountStatementService;

@Service
public class AccountStatementImpl implements AccountStatementService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	StatementRepository statementRepository;
	@Autowired
	AccountRepository accountRepository;

	public List<Statement> search(Long accountId, Date fromDate, Date toDate, Double lower, Double higher) {
		return statementRepository.findAll();
	}

	@Override
	public List<Statement> getStatements() {
		logger.info("GetStatements()");
		return statementRepository.findAll();
	}

	@Override
	public List<Statement> getStatementsByAccountId(Long accountId, Date fromDate, Date toDate, Double lower,
			Double higher) {
		
		logger.info("GetStatementsByAccountId()");

		List<Predicate<Statement>> predicates = predicateBuilder(accountId, fromDate, toDate, lower, higher);
		
		return statementRepository.findAll().stream()
				.filter(predicates.stream().reduce(predicate -> true, Predicate::and)).sorted()
				.collect(Collectors.toList());
	}

	private List<Predicate<Statement>> predicateBuilder(Long accountId, Date fromDate, Date toDate, Double lower,
			Double higher) {

		List<Predicate<Statement>> predicates = new ArrayList<Predicate<Statement>>();

		Predicate<Statement> accountIdPredicate = statement -> statement.getAccountId().getId().equals(accountId);
		Predicate<Statement> fromPredicate = statement -> statement.getDatefield().after(fromDate);
		Predicate<Statement> toPredicate = statement -> statement.getDatefield().before(toDate);
		Predicate<Statement> lowerPredicate = statement -> statement.getAmount() >= (lower);
		Predicate<Statement> higherPredicate = statement -> statement.getAmount() <= higher;

		if (accountId != null)
			predicates.add(accountIdPredicate);

		if (fromDate != null)
			predicates.add(fromPredicate);

		if (toDate != null)
			predicates.add(toPredicate);

		if (lower != null)
			predicates.add(lowerPredicate);

		if (higher != null)
			predicates.add(higherPredicate);

		if (predicates.isEmpty()) {
			Date threeMonths = new Date();
			threeMonths.setMonth(threeMonths.getMonth() - 3);
			predicates.add(x -> x.getDatefield().after(threeMonths));
		}

		return predicates;
	}

	@Override
	public List<Account> getAccounts() {
		logger.info("GetAccounts()");
		return accountRepository.findAll();
	}

}