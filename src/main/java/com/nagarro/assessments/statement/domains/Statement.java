package com.nagarro.assessments.statement.domains;

import java.util.Date;

import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.Table;

//import com.nagaro.engine.model.converters.AmountConverter;
//import com.nagaro.engine.model.converters.DateConverter;

@Entity
@Table(name = "statement")
public class Statement {

    @Id
    // @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name="account_id")
    private Account accountId;

    //@Convert(converter = DateConverter.class)
    @Column(name="datefield")
    private Date datefield;

    @Column(name="amount")
    //@Convert(converter = AmountConverter.class)
    private Double amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccountId() {
        return accountId;
    }

    public void setAccountId(Account accountID) {
        this.accountId = accountID;
    }

    public Date getDatefield() {
        return datefield;
    }

    public void setDatefield(Date datefield) {
        this.datefield = datefield;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Statement [accountId=" + accountId.getAccountNumber() + ", amount=" + amount + ", datefield=" + datefield + ", id=" + id
                + "]";
    }


}