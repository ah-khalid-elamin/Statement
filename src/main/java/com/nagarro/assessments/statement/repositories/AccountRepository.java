package com.nagarro.assessments.statement.repositories;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import com.nagarro.assessments.statement.domains.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    
}