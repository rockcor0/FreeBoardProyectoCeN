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
import javax.persistence.Lob;
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
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"idauctions"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Auctions.findAll", query = "SELECT a FROM Auctions a")
    , @NamedQuery(name = "Auctions.findByIdauctions", query = "SELECT a FROM Auctions a WHERE a.idauctions = :idauctions")
    , @NamedQuery(name = "Auctions.findByType", query = "SELECT a FROM Auctions a WHERE a.type = :type")
    , @NamedQuery(name = "Auctions.findBySize", query = "SELECT a FROM Auctions a WHERE a.size = :size")
    , @NamedQuery(name = "Auctions.findByMainColor", query = "SELECT a FROM Auctions a WHERE a.mainColor = :mainColor")
    , @NamedQuery(name = "Auctions.findBySecundaryColor", query = "SELECT a FROM Auctions a WHERE a.secundaryColor = :secundaryColor")
    , @NamedQuery(name = "Auctions.findByDescription", query = "SELECT a FROM Auctions a WHERE a.description = :description")
    , @NamedQuery(name = "Auctions.findByTime", query = "SELECT a FROM Auctions a WHERE a.time = :time")
    , @NamedQuery(name = "Auctions.findByPrice", query = "SELECT a FROM Auctions a WHERE a.price = :price")
    , @NamedQuery(name = "Auctions.findByCreated", query = "SELECT a FROM Auctions a WHERE a.created = :created")
    , @NamedQuery(name = "Auctions.findByUpdated", query = "SELECT a FROM Auctions a WHERE a.updated = :updated")})
public class Auctions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false, length = 32)
    private String idauctions;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String type;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String size;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String mainColor;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String secundaryColor;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String description;
    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String price;
    @Lob
    private byte[] sketch;
    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auctionsIdauctions")
    private List<Offers> offersList;
    @JoinColumn(name = "companies_id", referencedColumnName = "email", nullable = false)
    @ManyToOne(optional = false)
    private Companies companiesId;
    @JoinColumn(name = "winnerOffer", referencedColumnName = "idoffers", nullable = false)
    @ManyToOne(optional = false)
    private Offers winnerOffer;

    public Auctions() {
    }

    public Auctions(String idauctions) {
        this.idauctions = idauctions;
    }

    public Auctions(String idauctions, String type, String size, String mainColor, String secundaryColor, String description, Date time, String price, Date created) {
        this.idauctions = idauctions;
        this.type = type;
        this.size = size;
        this.mainColor = mainColor;
        this.secundaryColor = secundaryColor;
        this.description = description;
        this.time = time;
        this.price = price;
        this.created = created;
    }

    public String getIdauctions() {
        return idauctions;
    }

    public void setIdauctions(String idauctions) {
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

    public byte[] getSketch() {
        return sketch;
    }

    public void setSketch(byte[] sketch) {
        this.sketch = sketch;
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

    public Companies getCompaniesId() {
        return companiesId;
    }

    public void setCompaniesId(Companies companiesId) {
        this.companiesId = companiesId;
    }

    public Offers getWinnerOffer() {
        return winnerOffer;
    }

    public void setWinnerOffer(Offers winnerOffer) {
        this.winnerOffer = winnerOffer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idauctions != null ? idauctions.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Auctions)) {
            return false;
        }
        Auctions other = (Auctions) object;
        if ((this.idauctions == null && other.idauctions != null) || (this.idauctions != null && !this.idauctions.equals(other.idauctions))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beansexample.Auctions[ idauctions=" + idauctions + " ]";
    }
    
}
