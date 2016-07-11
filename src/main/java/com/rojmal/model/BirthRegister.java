package com.rojmal.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
@Table(name = "birthregister")
@JsonSerialize(include = Inclusion.NON_NULL)
public class BirthRegister implements Serializable {
	@Id
	private String id;

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created")
	private Date created;
	private String firstname;
	private String lastname;
	private String sex;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dateofbirth")
	private Date dateofbirth;
	private String nameoffather;
	private String nameofmother;
	private String birthpalce;
	private String parmanentaddress;
	private String registrationno;
	private String remarks;
	private String dateofregistration;
	private String zone;
	private String ward;
	private String birthmonth;
	private String sun;
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinColumn(name = "regisation")
	private Regisation regisation;
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated")
	private Date updated;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@PrePersist
	public void idSet() {
		this.id = UUID.randomUUID().toString();
		this.created = new Date();
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public String getNameoffather() {
		return nameoffather;
	}

	public void setNameoffather(String nameoffather) {
		this.nameoffather = nameoffather;
	}

	public void setNameofmother(String nameofmother) {
		this.nameofmother = nameofmother;
	}

	public String getNameofmother() {
		return nameofmother;
	}

	public String getBirthpalce() {
		return birthpalce;
	}

	public void setBirthpalce(String birthpalce) {
		this.birthpalce = birthpalce;
	}

	public String getParmanentaddress() {
		return parmanentaddress;
	}

	public void setParmanentaddress(String parmanentaddress) {
		this.parmanentaddress = parmanentaddress;
	}

	public String getRegistrationno() {
		return registrationno;
	}

	public void setRegistrationno(String registrationno) {
		this.registrationno = registrationno;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getDateofregistration() {
		return dateofregistration;
	}

	public void setDateofregistration(String dateofregistration) {
		this.dateofregistration = dateofregistration;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	public String getBirthmonth() {
		return birthmonth;
	}

	public void setBirthmonth(String birthmonth) {
		this.birthmonth = birthmonth;
	}

	public String getSun() {
		return sun;
	}

	public void setSun(String sun) {
		this.sun = sun;
	}

	public Regisation getRegisation() {
		return regisation;
	}

	public void setRegisation(Regisation regisation) {
		this.regisation = regisation;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}
}
