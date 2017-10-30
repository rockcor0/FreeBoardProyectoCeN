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
@Table(name = "companies", uniqueConstraints = { @UniqueConstraint(columnNames = { "email" }) })
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Companies.findAll", query = "SELECT c FROM Companies c"),
		@NamedQuery(name = "Companies.findByEmail", query = "SELECT c FROM Companies c WHERE c.email = :email"),
		@NamedQuery(name = "Companies.findByName", query = "SELECT c FROM Companies c WHERE c.name = :name"),
		@NamedQuery(name = "Companies.findByPhone", query = "SELECT c FROM Companies c WHERE c.phone = :phone"),
		@NamedQuery(name = "Companies.findByAddress", query = "SELECT c FROM Companies c WHERE c.address = :address"),
		@NamedQuery(name = "Companies.findByPassword", query = "SELECT c FROM Companies c WHERE c.password = :password"),
		@NamedQuery(name = "Companies.findByContactPerson", query = "SELECT c FROM Companies c WHERE c.contactPerson = :contactPerson"),
		@NamedQuery(name = "Companies.findByHash", query = "SELECT c FROM Companies c WHERE c.hash = :hash"),
		@NamedQuery(name = "Companies.findByCreated", query = "SELECT c FROM Companies c WHERE c.created = :created"),
		@NamedQuery(name = "Companies.findByUpdated", query = "SELECT c FROM Companies c WHERE c.updated = :updated") })
public class Companies implements Serializable {

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
	private String phone;

	@Basic(optional = false)
	@Column(nullable = false, length = 45)
	private String address;

	@Basic(optional = false)
	@Column(nullable = false, length = 45)
	@JsonIgnore
	private String password;

	@Basic(optional = false)
	@Column(nullable = false, length = 45)
	private String contactPerson;

	@Basic(optional = false)
	@Column(nullable = false, length = 32)
	private String hash;

	@Basic(optional = false)
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date updated;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "companiesId")
	private List<Auctions> auctionsList;

	public Companies() {
	}

	public Companies(String email) {
		this.email = email;
	}

	public Companies(String email, String name, String phone, String address, String password, String contactPerson,
			String hash, Date created) {
		this.email = email;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.password = password;
		this.contactPerson = contactPerson;
		this.hash = hash;
		this.created = created;
	}

	public Companies(String email2, String name2, String phone2, String address2, String password2,
			String contactPerson2) {
		this.email = email2;
		this.name = name2;
		this.phone = phone2;
		this.address = address2;
		this.password = password2;
		this.contactPerson = contactPerson2;
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
	public List<Auctions> getAuctionsList() {
		return auctionsList;
	}

	public void setAuctionsList(List<Auctions> auctionsList) {
		this.auctionsList = auctionsList;
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
		if (!(object instanceof Companies)) {
			return false;
		}
		Companies other = (Companies) object;
		if ((this.email == null && other.email != null) || (this.email != null && !this.email.equals(other.email))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "beansexample.Companies[ email=" + email + " ]";
	}

}