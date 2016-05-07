package com.rojmal.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
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
@Table(name = "bank")
@JsonSerialize(include = Inclusion.NON_NULL)
public class Bank implements Serializable {

	@Id
	private String id;

	private String name;

	private Double openbal;

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "regid")
	private Regisation regisation;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getOpenbal() {
		return openbal;
	}

	public void setOpenbal(Double openbal) {
		this.openbal = openbal;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@PrePersist
	public void idSet() {
		this.id = UUID.randomUUID().toString();
		this.created = new Date();
	}

	public Regisation getRegisation() {
		return regisation;
	}

	public void setRegisation(Regisation regisation) {
		this.regisation = regisation;
	}

}
