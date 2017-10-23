package com.project.freeboard.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "students")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Students.getAll", query = "SELECT s FROM Students s"),
		@NamedQuery(name = "Students.findById", query = "SELECT s FROM Students s WHERE s.cc = :id") })
public class Students implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@Column(name = "cc")
	private String cc;

	@Basic(optional = false)
	@Column(name = "name")
	private String name;

	@Basic(optional = false)
	@Column(name = "email")
	private String email;

	@Basic(optional = false)
	@Column(name = "phone")
	private String phone;

	@Basic(optional = false)
	@Column(name = "bankWire")
	private String bankWire;

	@Basic(optional = false)
	@Column(name = "bank")
	private String bank;

	@Basic(optional = false)
	@Column(name = "accountType")
	private String accountType;

	@Basic(optional = false)
	@Column(name = "university")
	private String university;

	@Basic(optional = false)
	@Column(name = "career")
	private String career;

	@Basic(optional = false)
	@Column(name = "titular")
	private String titular;

	@Basic(optional = false)
	@Column(name = "experiencia")
	private String experiencia;

	@Basic(optional = false)
	@Column(name = "skills")
	private String skills;

	@Basic(optional = false)
	@Column(name = "password")
	private String password;

	public Students() {
		super();
	}

	public Students(String cc, String name, String email, String phone, String bankWire, String bank,
			String accountType, String university, String career, String titular, String experiencia, String skills,
			String password) {
		super();
		this.cc = cc;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.bankWire = bankWire;
		this.bank = bank;
		this.accountType = accountType;
		this.university = university;
		this.career = career;
		this.titular = titular;
		this.experiencia = experiencia;
		this.skills = skills;
		this.password = password;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBankWire() {
		return bankWire;
	}

	public void setBankWire(String bankWire) {
		this.bankWire = bankWire;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public String getCareer() {
		return career;
	}

	public void setCareer(String career) {
		this.career = career;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public String getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(String experiencia) {
		this.experiencia = experiencia;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cc == null) ? 0 : cc.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Students other = (Students) obj;
		if (cc == null) {
			if (other.cc != null)
				return false;
		} else if (!cc.equals(other.cc))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Students [cc=" + cc + ", name=" + name + ", email=" + email + ", phone=" + phone + ", bankWire="
				+ bankWire + ", bank=" + bank + ", accountType=" + accountType + ", university=" + university
				+ ", career=" + career + ", titular=" + titular + ", experiencia=" + experiencia + ", skills=" + skills
				+ ", password=" + password + "]";
	}

}
