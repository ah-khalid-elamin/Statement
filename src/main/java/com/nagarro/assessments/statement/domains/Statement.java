package com.nagarro.assessments.statement.domains;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.nagarro.assessments.statement.domains.converters.AmountConverter;
import com.nagarro.assessments.statement.domains.converters.DateConverter;


@Entity
@Table(name = "statement")
public class Statement implements Comparable<Statement> {

    @Id
    // @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name="account_id")
    private Account accountId;

    @Convert(converter = DateConverter.class)
    @Column(name="datefield")
    private Date datefield;

    @Column(name="amount")
    @Convert(converter = AmountConverter.class)
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

	@Override
	public int compareTo(Statement object) {
		return this.id.compareTo(object.id);
	}


}