package com.rojmal.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

@Entity
@Table(name = "transaction")
@JsonSerialize(include = Inclusion.NON_NULL)
public class Transaction {

	@Id
	private String id;
	private Double amount;
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	
	@Enumerated(EnumType.STRING)
	private Type ttType;
	
	@Enumerated(EnumType.STRING)
	private Paymenttype paymenttype;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "regid")
	private Regisation regisation;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bankid")
	private Bank bank;
	
	private String des;
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Type getTtType() {
		return ttType;
	}

	public void setTtType(Type ttType) {
		this.ttType = ttType;
	}

	public Paymenttype getPaymenttype() {
		return paymenttype;
	}
	public void setPaymenttype(Paymenttype paymenttype) {
		this.paymenttype = paymenttype;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public enum Type {
		CREDIT, DEBIT
	}

	public enum Paymenttype {
		BANK, CASH
	}

	@PrePersist
	public void idSet() {
		this.id = UUID.randomUUID().toString();
		this.created = new Date();
	}
}
