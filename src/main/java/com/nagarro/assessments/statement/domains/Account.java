package com.nagarro.assessments.statement.domains;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.nagarro.assessments.statement.domains.converters.AccountConverter;


@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="account_type")
    private String accountType;

    @Convert(converter = AccountConverter.class)
    @Column(name="account_number")
    private String accountNumber;

    // @OneToMany //(mappedBy = "ID")
    // private List<Statement> statements;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    // public List<Statement> getStatements() {
    //     return statements;
    // }

    // public void setStatements(List<Statement> statements) {
    //     this.statements = statements;
    // }
}