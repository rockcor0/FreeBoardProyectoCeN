package com.project.freeboard.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="payment")
public class Payment implements Serializable {
	
	

	private static final long serialVersionUID = 1L;
	
	@Id
	@Basic(optional = false)
	@Column(name = "idpagos")
	private int idPagos;
	
	/**
	 * Es la referencia de la venta o pedido. Deber ser único por cada transacción que se envía al sistema. 
	 * Normalmente es una forma de que identifiques las peticiones que se envían a la pasarela de pagos.
	 */
	@Basic(optional = false)
	@Column(name = "referenceCode")
	private String referenceCode;
	
	/**
	 * Es el monto total de la transacción. Puede contener dos dígitos decimales. Ej. 10000.00 ó 10000.
	 */
	@Basic(optional = false)
	@Column(name = "amount")
	private double amount;
	
	/**
	 * Es el valor del IVA (Impuesto al Valor Agregado solo valido para Colombia) de la transacción, 
	 * si se envía el IVA nulo el sistema aplicará el 19% automáticamente. 
	 * Puede contener dos dígitos decimales. Ej: 19000.00. En caso de no tener IVA debe enviarse en 0.
	 */
	@Basic(optional = false)
	@Column(name = "tax")
	private double tax;

	/**
	 * Es el valor base sobre el cual se calcula el IVA (solo valido para Colombia). 
	 * En caso de que no tenga IVA debe enviarse en 0.
	 */
	@Basic(optional = false)
	@Column(name = "taxReturnBase")
	private int taxReturnBase;
	
	/**
	 * La moneda respectiva en la que se realiza el pago. 
	 * El proceso de conciliación se hace en pesos a la tasa representativa del día.
	 */
	@Basic(optional = false)
	@Column(name = "currency")
	private String currency;
	
	/**
	 * Es la firma digital creada para cada uno de las transacciones.
	 */
	@Basic(optional = false)
	@Column(name = "signature")
	private String signature;
	
	/**
	 * Nombre completo del comprador.
	 */
	@Basic(optional = false)
	@Column(name = "buyerFullName")
	private String buyerFullName;
	
	/**
	 * Campo que contiene el correo electrónico del comprador para notificarle el resultado de la transacción 
	 * por correo electrónico. Se recomienda hacer una validación si se toma este dato en un formulario.
	 */
	@Basic(optional = false)
	@Column(name = "buyerEmail")
	private String buyerEmail;
	
	public Payment(int idPagos, String referenceCode, double amount, double tax, int taxReturnBase, String currency,
			String signature, String buyerFullName, String buyerEmail) {
		super();
		this.idPagos = idPagos;
		this.referenceCode = referenceCode;
		this.amount = amount;
		this.tax = tax;
		this.taxReturnBase = taxReturnBase;
		this.currency = currency;
		this.signature = signature;
		this.buyerFullName = buyerFullName;
		this.buyerEmail = buyerEmail;
	}

	public int getIdPagos() {
		return idPagos;
	}

	public void setIdPagos(int idPagos) {
		this.idPagos = idPagos;
	}

	public String getReferenceCode() {
		return referenceCode;
	}

	public void setReferenceCode(String referenceCode) {
		this.referenceCode = referenceCode;
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

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
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
	
	
	

}
