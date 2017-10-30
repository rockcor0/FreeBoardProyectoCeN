package com.project.freeboard.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nicolas1
 */
@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"referenceCode"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transactions.findAll", query = "SELECT t FROM Transactions t")
    , @NamedQuery(name = "Transactions.findByReferenceCode", query = "SELECT t FROM Transactions t WHERE t.referenceCode = :referenceCode")
    , @NamedQuery(name = "Transactions.findByDescription", query = "SELECT t FROM Transactions t WHERE t.description = :description")
    , @NamedQuery(name = "Transactions.findByAmount", query = "SELECT t FROM Transactions t WHERE t.amount = :amount")
    , @NamedQuery(name = "Transactions.findByTax", query = "SELECT t FROM Transactions t WHERE t.tax = :tax")
    , @NamedQuery(name = "Transactions.findByTaxReturnBase", query = "SELECT t FROM Transactions t WHERE t.taxReturnBase = :taxReturnBase")
    , @NamedQuery(name = "Transactions.findByCurrency", query = "SELECT t FROM Transactions t WHERE t.currency = :currency")
    , @NamedQuery(name = "Transactions.findByBuyerFullName", query = "SELECT t FROM Transactions t WHERE t.buyerFullName = :buyerFullName")
    , @NamedQuery(name = "Transactions.findByBuyerEmail", query = "SELECT t FROM Transactions t WHERE t.buyerEmail = :buyerEmail")
    , @NamedQuery(name = "Transactions.findByTest", query = "SELECT t FROM Transactions t WHERE t.test = :test")
    , @NamedQuery(name = "Transactions.findByPayHash", query = "SELECT t FROM Transactions t WHERE t.payHash = :payHash")
    , @NamedQuery(name = "Transactions.findByStatePol", query = "SELECT t FROM Transactions t WHERE t.statePol = :statePol")
    , @NamedQuery(name = "Transactions.findByResponseCodePol", query = "SELECT t FROM Transactions t WHERE t.responseCodePol = :responseCodePol")
    , @NamedQuery(name = "Transactions.findByResponseMessageCol", query = "SELECT t FROM Transactions t WHERE t.responseMessagePol = :responseMessageCol")
    , @NamedQuery(name = "Transactions.findByPaymentMethodType", query = "SELECT t FROM Transactions t WHERE t.paymentMethodType = :paymentMethodType")
    , @NamedQuery(name = "Transactions.findByTransactionDate", query = "SELECT t FROM Transactions t WHERE t.transactionDate = :transactionDate")
    , @NamedQuery(name = "Transactions.findByPaymentMethodName", query = "SELECT t FROM Transactions t WHERE t.paymentMethodName = :paymentMethodName")
    , @NamedQuery(name = "Transactions.findByTransactionscol", query = "SELECT t FROM Transactions t WHERE t.transactionscol = :transactionscol")
    , @NamedQuery(name = "Transactions.findByCreated", query = "SELECT t FROM Transactions t WHERE t.created = :created")
    , @NamedQuery(name = "Transactions.findByUpdated", query = "SELECT t FROM Transactions t WHERE t.updated = :updated")})


public class Transactions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false, length = 100)
    private String referenceCode;
    @Basic(optional = false)
    @Column(nullable = false, length = 500)
    private String description;
    @Basic(optional = false)
    @Column(nullable = false)
    private double amount;
    @Basic(optional = false)
    @Column(nullable = false)
    private double tax;
    @Basic(optional = false)
    @Column(nullable = false)
    private int taxReturnBase;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String currency;
    @Basic(optional = false)
    @Column(nullable = false, length = 100)
    private String buyerFullName;
    @Basic(optional = false)
    @Column(nullable = false, length = 100)
    private String buyerEmail;
    @Basic(optional = false)
    @Column(nullable = false, length = 10)
    private String test;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String payHash;
    @Column(name = "state_pol")
    private Integer statePol;
    @Column(name = "response_code_pol", length = 255)
    private Integer responseCodePol;
    @Column(name = "response_message_Pol")
    private String responseMessagePol;
    @Column(name = "payment_method_type")
    private Integer paymentMethodType;
    @Column(name = "transaction_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate;
    @Column(name = "payment_method_name", length = 255)
    private String paymentMethodName;
    @Column(length = 45)
    private String transactionscol;
    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;
    @JoinColumn(name = "offers_idoffers", referencedColumnName = "idoffers", nullable = false)
    @ManyToOne(optional = false)
    private Offers offersIdoffers;

    public Transactions() {
    }

    public Transactions(String referenceCode) {
        this.referenceCode = referenceCode;
    }

    public Transactions(String referenceCode, String description, double amount, double tax, int taxReturnBase, String currency, String buyerFullName, String buyerEmail, String test, String responseURL, String confirmationURL, String payHash, Date created) {
        this.referenceCode = referenceCode;
        this.description = description;
        this.amount = amount;
        this.tax = tax;
        this.taxReturnBase = taxReturnBase;
        this.currency = currency;
        this.buyerFullName = buyerFullName;
        this.buyerEmail = buyerEmail;
        this.test = test;
        this.payHash = payHash;
        this.created = created;
    }

    public String getReferenceCode() {
        return referenceCode;
    }

    public void setReferenceCode(String referenceCode) {
        this.referenceCode = referenceCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public int getTaxReturnBase() {
        return taxReturnBase;
    }

    public void setTaxReturnBase(int taxReturnBase) {
        this.taxReturnBase = taxReturnBase;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getBuyerFullName() {
        return buyerFullName;
    }

    public void setBuyerFullName(String buyerFullName) {
        this.buyerFullName = buyerFullName;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }


    public String getPayHash() {
        return payHash;
    }

    public void setPayHash(String payHash) {
        this.payHash = payHash;
    }

    public Integer getStatePol() {
        return statePol;
    }

    public void setStatePol(Integer statePol) {
        this.statePol = statePol;
    }

    public Integer getResponseCodePol() {
        return responseCodePol;
    }

    public void setResponseCodePol(Integer responseCodePol) {
        this.responseCodePol = responseCodePol;
    }

    public String getResponseMessagePol() {
        return responseMessagePol;
    }

    public void setResponseMessageCol(String responseMessageCol) {
        this.responseMessagePol = responseMessageCol;
    }

    public Integer getPaymentMethodType() {
        return paymentMethodType;
    }

    public void setPaymentMethodType(Integer paymentMethodType) {
        this.paymentMethodType = paymentMethodType;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getPaymentMethodName() {
        return paymentMethodName;
    }

    public void setPaymentMethodName(String paymentMethodName) {
        this.paymentMethodName = paymentMethodName;
    }

    public String getTransactionscol() {
        return transactionscol;
    }

    public void setTransactionscol(String transactionscol) {
        this.transactionscol = transactionscol;
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

    public Offers getOffersIdoffers() {
        return offersIdoffers;
    }

    public void setOffersIdoffers(Offers offersIdoffers) {
        this.offersIdoffers = offersIdoffers;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (referenceCode != null ? referenceCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transactions)) {
            return false;
        }
        Transactions other = (Transactions) object;
        if ((this.referenceCode == null && other.referenceCode != null) || (this.referenceCode != null && !this.referenceCode.equals(other.referenceCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beansexample.Transactions[ referenceCode=" + referenceCode + " ]";
    }
    
}