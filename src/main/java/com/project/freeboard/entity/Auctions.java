package com.project.freeboard.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.mysql.jdbc.Blob;

@Entity
@Table(name = "auctions")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Auctions.getAll", query = "SELECT a FROM Auctions a"),
	@NamedQuery(name = "Auctions.findById", query = "SELECT a FROM Auctions a WHERE a.idauctions = :idauctions"),
	@NamedQuery(name = "Auctions.findByType", query = "SELECT a FROM Auctions a WHERE a.type = :type"),
	@NamedQuery(name = "Auctions.findByTime", query = "SELECT a FROM Auctions a WHERE a.time = :time"),
	@NamedQuery(name = "Auctions.findByPrice", query = "SELECT a FROM Auctions a WHERE a.price = :price")})
public class Auctions implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@Column(name = "idauctions")
	private int idauctions;

	@Basic(optional = false)
	@Column(name = "type")
	private String type;

	@Basic(optional = false)
	@Column(name = "size")
	private String size;

	@Basic(optional = false)
	@Column(name = "mainColor")
	private String mainColor;

	@Basic(optional = false)
	@Column(name = "secundaryColor")
	private String secundaryColor;

	@Basic(optional = false)
	@Column(name = "description")
	private String description;

	@Basic(optional = false)
	@Column(name = "time")
	private Date time;

	@Basic(optional = false)
	@Column(name = "price")
	private String price;

	@Basic(optional = false)
	@Column(name = "sketch")
	private Blob sketch;

	@Basic(optional = false)
	@Column(name = "companies_nit")
	private String companies_nit;

	@Basic(optional = false)
	@Column(name = "winnerOffer")
	private int winnerOffer;

	public Auctions() {
		super();
	}

	public Auctions(int idauctions, String type, String size, String mainColor, String secundaryColor,
			String description, Date time, String price, Blob sketch, String companies_nit, int winnerOffer) {
		super();
		this.idauctions = idauctions;
		this.type = type;
		this.size = size;
		this.mainColor = mainColor;
		this.secundaryColor = secundaryColor;
		this.description = description;
		this.time = time;
		this.price = price;
		this.sketch = sketch;
		this.companies_nit = companies_nit;
		this.winnerOffer = winnerOffer;
	}

	public Auctions(int idauctions, String type, String size, String mainColor, String secundaryColor,
			String description, Date time, String price, Blob sketch) {
		super();
		this.idauctions = idauctions;
		this.type = type;
		this.size = size;
		this.mainColor = mainColor;
		this.secundaryColor = secundaryColor;
		this.description = description;
		this.time = time;
		this.price = price;
		this.sketch = sketch;
	}

	public int getIdauctions() {
		return idauctions;
	}

	public void setIdauctions(int idauctions) {
		this.idauctions = idauctions;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getMainColor() {
		return mainColor;
	}

	public void setMainColor(String mainColor) {
		this.mainColor = mainColor;
	}

	public String getSecundaryColor() {
		return secundaryColor;
	}

	public void setSecundaryColor(String secundaryColor) {
		this.secundaryColor = secundaryColor;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Blob getSketch() {
		return sketch;
	}

	public void setSketch(Blob sketch) {
		this.sketch = sketch;
	}

	public String getCompanies_nit() {
		return companies_nit;
	}

	public void setCompanies_init(String companies_nit) {
		this.companies_nit = companies_nit;
	}

	public int getWinnerOffer() {
		return winnerOffer;
	}

	public void setWinnerOffer(int winnerOffer) {
		this.winnerOffer = winnerOffer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((new Integer(idauctions) == null) ? 0 : new Integer(idauctions).hashCode());
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
		Auctions other = (Auctions) obj;
		if (!new Integer(idauctions).equals(new Integer(other.idauctions)))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Auction [idauctions=" + idauctions + ", type=" + type + ", size=" + size + ", mainColor=" + mainColor
				+ ", secundaryColor=" + secundaryColor + ", description=" + description + ", time=" + time + ", price="
				+ price + ", sketch=" + sketch + ", companies_nit=" + companies_nit + ", winnerOffer=" + winnerOffer
				+ "]";
	}

}
