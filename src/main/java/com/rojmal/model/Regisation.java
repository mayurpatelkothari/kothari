package com.rojmal.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

@Entity
@Table(name = "regisation")
@JsonSerialize(include = Inclusion.NON_NULL)
public class Regisation implements Serializable {

	@Id
	private String id;
	private String username;

	private String password;

	@Enumerated(EnumType.STRING)
	private Role role;

	private Double openbal;

	private BalType baltype;

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public static enum Role {
		ADMIN, USER;

		public String getAuthority() {
			return "ROLE_" + name();
		}
	}

	// boolean hasRole(Role role) {
	// return this.getRole().contain(role);
	// }

	public boolean hasOnlyRole(Role role) {
		return this.role.equals(role);
	}

	@PrePersist
	public void idSet() {
		this.id = UUID.randomUUID().toString();
		this.created = new Date();
	}

	public Double getOpenbal() {
		return openbal;
	}

	public void setOpenbal(Double openbal) {
		this.openbal = openbal;
	}

	public enum BalType {
		CREDIT, DEBIT
	}

	public BalType getBaltype() {
		return baltype;
	}

	public void setBaltype(BalType baltype) {
		this.baltype = baltype;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
}
