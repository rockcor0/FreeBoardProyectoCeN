package com.project.freeboard.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="companies")
public class Offers implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@Column(name = "idoffers")
	private int idoffers;
	
	@Basic(optional = false)
	@Column(name = "price")
	private String price;
	
	@Basic(optional = false)
	@Column(name = "students_cc")
	private String students_cc;
	
	@Basic(optional = false)
	@Column(name = "auctions_idauctions")
	private int auctions_idauctions;
	public Offers(int idoffers, String price, String students_cc, int auctions_idauctions) {
		super();
		this.idoffers = idoffers;
		this.price = price;
		this.students_cc = students_cc;
		this.auctions_idauctions = auctions_idauctions;
	}
	public int getIdoffers() {
		return idoffers;
	}
	public void setIdoffers(int idoffers) {
		this.idoffers = idoffers;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getStudents_cc() {
		return students_cc;
	}
	public void setStudents_cc(String students_cc) {
		this.students_cc = students_cc;
	}
	public int getAuctions_idauctions() {
		return auctions_idauctions;
	}
	public void setAuctions_idauctions(int auctions_idauctions) {
		this.auctions_idauctions = auctions_idauctions;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((new Integer(idoffers) == null) ? 0 : new Integer(idoffers).hashCode());
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
		Offers other = (Offers) obj;
		if (!new Integer(idoffers).equals(new Integer(other.idoffers)))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Offer [idOffers=" + idoffers + ", price=" + price + ", students_cc=" + students_cc + ", auctions_idauctions="
				+ auctions_idauctions + "]";
	}
	
}
