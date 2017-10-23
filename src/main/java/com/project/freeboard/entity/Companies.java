package com.project.freeboard.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="companies")

public class Companies implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@Column(name = "nit")
	private String nit;
	
	@Basic(optional = false)
	@Column(name = "name")
	private String name;
	
	@Basic(optional = false)
	@Column(name = "phone")
	private String phone;
	
	@Basic(optional = false)
	@Column(name = "address")
	private String address;
	
	@Basic(optional = false)
	@Column(name = "mail")
	private String mail;
	
	@Basic(optional = false)
	@Column(name = "password")
	private String password;
	
	@Basic(optional = false)
	@Column(name = "contactPerson")
	private String contactPerson;
	
	public Companies(String nit, String name, String phone, String address, String mail, String password,
			String contactPerson) {
		super();
		this.nit = nit;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.mail = mail;
		this.password = password;
		this.contactPerson = contactPerson;
	}
	public String getNit() {
		return nit;
	}
	public void setNit(String nit) {
		this.nit = nit;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	
	
}
