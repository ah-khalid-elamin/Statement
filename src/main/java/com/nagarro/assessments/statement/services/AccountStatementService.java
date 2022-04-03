package com.nagarro.assessments.statement.services;

import java.util.Date;
import java.util.List;

import com.nagarro.assessments.statement.domains.Account;
import com.nagarro.assessments.statement.domains.Statement;

public interface AccountStatementService {
    public List<Statement> getStatements();
    public List<Statement> getStatementsByAccountId(Long accountId, Date fromDate, Date toDate, Double lower, Double higher);
    public List<Account> getAccounts();

}