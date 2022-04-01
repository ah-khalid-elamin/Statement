package com.nagarro.assessments.statement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.assessments.statement.domains.Statement;

@Repository
public interface StatementRepository extends JpaRepository<Statement, Long>{

}