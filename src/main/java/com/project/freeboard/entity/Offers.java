package com.project.freeboard.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author nicolas1
 */
@Entity
@Table(name = "offers", uniqueConstraints = { @UniqueConstraint(columnNames = { "idoffers" }) })
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Offers.findAll", query = "SELECT o FROM Offers o"),
		@NamedQuery(name = "Offers.findByIdoffers", query = "SELECT o FROM Offers o WHERE o.idoffers = :idoffers"),
		@NamedQuery(name = "Offers.findByPrice", query = "SELECT o FROM Offers o WHERE o.price = :price"),
		@NamedQuery(name = "Offers.findByPaid", query = "SELECT o FROM Offers o WHERE o.paid = :paid"),
		@NamedQuery(name = "Offers.findByPayHash", query = "SELECT o FROM Offers o WHERE o.payHash = :payHash"),
		@NamedQuery(name = "Offers.findByState", query = "SELECT o FROM Offers o WHERE o.state = :state"),
		@NamedQuery(name = "Offers.findByCreated", query = "SELECT o FROM Offers o WHERE o.created = :created"),
		@NamedQuery(name = "Offers.findByUpdated", query = "SELECT o FROM Offers o WHERE o.updated = :updated") })
public class Offers implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(nullable = false, length = 32)
	private String idoffers;
	@Column(length = 45)
	private String price;
	private Short paid;
	@Column(length = 45)
	private String payHash;
	@Basic(optional = false)
	@Column(nullable = false, length = 45)
	private String state;
	@Basic(optional = false)
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;
	@JoinColumn(name = "auctions_idauctions", referencedColumnName = "idauctions", nullable = false)
	@ManyToOne(optional = false)
	private Auctions auctionsIdauctions;
	@JoinColumn(name = "students_id", referencedColumnName = "email", nullable = false)
	@ManyToOne(optional = false)
	private Students studentsId;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "offersIdoffers")
	private List<Transactions> transactionsList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "companiesId")
	private List<Auctions> auctionsList;

	public Offers() {
	}

	public Offers(String idoffers) {
		this.idoffers = idoffers;
	}

	public Offers(String idoffers, String state, Date created) {
		this.idoffers = idoffers;
		this.state = state;
		this.created = created;
	}

	public String getIdoffers() {
		return idoffers;
	}

	public void setIdoffers(String idoffers) {
		this.idoffers = idoffers;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Short getPaid() {
		return paid;
	}

	public void setPaid(Short paid) {
		this.paid = paid;
	}

	public String getPayHash() {
		return payHash;
	}

	public void setPayHash(String payHash) {
		this.payHash = payHash;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public Auctions getAuctionsIdauctions() {
		return auctionsIdauctions;
	}

	public void setAuctionsIdauctions(Auctions auctionsIdauctions) {
		this.auctionsIdauctions = auctionsIdauctions;
	}

	public Students getStudentsId() {
		return studentsId;
	}

	public void setStudentsId(Students studentsId) {
		this.studentsId = studentsId;
	}

	@XmlTransient
	public List<Transactions> getTransactionsList() {
		return transactionsList;
	}

	public void setTransactionsList(List<Transactions> transactionsList) {
		this.transactionsList = transactionsList;
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
		hash += (idoffers != null ? idoffers.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Offers)) {
			return false;
		}
		Offers other = (Offers) object;
		if ((this.idoffers == null && other.idoffers != null)
				|| (this.idoffers != null && !this.idoffers.equals(other.idoffers))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "beansexample.Offers[ idoffers=" + idoffers + " ]";
	}

}