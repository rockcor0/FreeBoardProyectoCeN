package com.project.freeboard.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.google.appengine.repackaged.org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author nicolas1
 */
@Entity
@Table(name = "students", uniqueConstraints = { @UniqueConstraint(columnNames = { "email" }) })
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Students.getAll", query = "SELECT s FROM Students s"),
		@NamedQuery(name = "Students.findByEmail", query = "SELECT s FROM Students s WHERE s.email = :email"),
		@NamedQuery(name = "Students.findByName", query = "SELECT s FROM Students s WHERE s.name = :name"),
		@NamedQuery(name = "Students.findByLastname", query = "SELECT s FROM Students s WHERE s.lastname = :lastname"),
		@NamedQuery(name = "Students.findByPassword", query = "SELECT s FROM Students s WHERE s.password = :password"),
		@NamedQuery(name = "Students.findByUniversity", query = "SELECT s FROM Students s WHERE s.university = :university"),
		@NamedQuery(name = "Students.findByPhone", query = "SELECT s FROM Students s WHERE s.phone = :phone"),
		@NamedQuery(name = "Students.findByBankWire", query = "SELECT s FROM Students s WHERE s.bankWire = :bankWire"),
		@NamedQuery(name = "Students.findByBank", query = "SELECT s FROM Students s WHERE s.bank = :bank"),
		@NamedQuery(name = "Students.findByAccountType", query = "SELECT s FROM Students s WHERE s.accountType = :accountType"),
		@NamedQuery(name = "Students.findByAccountOwner", query = "SELECT s FROM Students s WHERE s.accountOwner = :accountOwner"),
		@NamedQuery(name = "Students.findByExperience", query = "SELECT s FROM Students s WHERE s.experience = :experience"),
		@NamedQuery(name = "Students.findBySkills", query = "SELECT s FROM Students s WHERE s.skills = :skills"),
		@NamedQuery(name = "Students.findByHash", query = "SELECT s FROM Students s WHERE s.hash = :hash"),
		@NamedQuery(name = "Students.findByCreated", query = "SELECT s FROM Students s WHERE s.created = :created"),
		@NamedQuery(name = "Students.findByUpdated", query = "SELECT s FROM Students s WHERE s.updated = :updated") })
public class Students implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(nullable = false, length = 45)
	private String email;
	@Basic(optional = false)
	@Column(nullable = false, length = 45)
	private String name;
	@Basic(optional = false)
	@Column(nullable = false, length = 45)
	private String lastname;
	@Basic(optional = false)
	@Column(nullable = false, length = 45)
	@JsonIgnore
	private String password;
	@Basic(optional = false)
	@Column(nullable = false, length = 45)
	private String university;
	@Basic(optional = false)
	@Column(nullable = false, length = 45)
	private String phone;
	@Basic(optional = false)
	@Column(nullable = false, length = 45)
	private String bankWire;
	@Basic(optional = false)
	@Column(nullable = false, length = 45)
	private String bank;
	@Basic(optional = false)
	@Column(nullable = false, length = 45)
	private String accountType;
	@Basic(optional = false)
	@Column(nullable = false, length = 45)
	private String accountOwner;
	@Column(length = 200)
	private String experience;
	@Column(length = 200)
	private String skills;
	@Basic(optional = false)
	@Column(nullable = false, length = 32)
	private String hash;
	@Basic(optional = false)
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "studentsId")
	@JsonIgnore
	private List<Offers> offersList;

	public Students() {
	}

	public Students(String email) {
		this.email = email;
	}

	public Students(String email, String name, String lastname, String password, String university, String phone,
			String bankWire, String bank, String accountType, String accountOwner, String hash, Date created) {
		this.email = email;
		this.name = name;
		this.lastname = lastname;
		this.password = password;
		this.university = university;
		this.phone = phone;
		this.bankWire = bankWire;
		this.bank = bank;
		this.accountType = accountType;
		this.accountOwner = accountOwner;
		this.hash = hash;
		this.created = created;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
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

	public String getAccountOwner() {
		return accountOwner;
	}

	public void setAccountOwner(String accountOwner) {
		this.accountOwner = accountOwner;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	@XmlTransient
	public List<Offers> getOffersList() {
		return offersList;
	}

	public void setOffersList(List<Offers> offersList) {
		this.offersList = offersList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (email != null ? email.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Students)) {
			return false;
		}
		Students other = (Students) object;
		if ((this.email == null && other.email != null) || (this.email != null && !this.email.equals(other.email))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "beansexample.Students[ email=" + email + " ]";
	}

}