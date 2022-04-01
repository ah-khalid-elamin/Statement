package com.nagarro.assessments.statement.services;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.nagarro.assessments.statement.domains.Statement;
import com.nagarro.assessments.statement.repositories.StatementRepository;

@Service
public class AccountStatmentService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StatementRepository statementRepository;

    // @Secured("ROLE_ADMIN")
    public List<Statement> adminSearch(Date from, Date to, Double higher, Double lower) {
        logger.info("Only Admins Can search with this method");
        return GetStatementsBy(from, to, higher, lower);
    }
    
    public List<Statement> GetStatements(){
    	return statementRepository.findAll();
    }
    public List<Statement> GetStatementsBy(Date from, Date to, Double higher, Double lower) {

        // List<Predicate<Statement>> predicates = predicateBuilder(from, to, higher,
        // lower);

        // List<Statement> s = statementRepository.findAll().stream().filter(
        // predicates.stream().reduce(x -> true, Predicate::and))
        // .collect(Collectors.toList());
        // s.forEach(System.out::println);
        // return s;
        return statementRepository.findAll();
    }

   

}