package com.project.freeboard.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.jdo.annotations.Transactional;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.response.NotFoundException;
import com.project.freeboard.dao.CompaniesDAO;
import com.project.freeboard.dao.TransactionsDAO;
import com.project.freeboard.entity.Companies;
import com.project.freeboard.entity.Transactions;

@Api(name = "freeboard", version = "v1", namespace = @ApiNamespace(ownerDomain = "service.freeboard.project.com", ownerName = "service.freeboard.project.com", packagePath = ""))
public class EpTransaction {

	private TransactionsDAO tDAO;
	
	/**
	 * API Company Entity
	 */
	@ApiMethod(name = "getTransactionById", path = "transactionById/{referenceCode}", httpMethod = ApiMethod.HttpMethod.GET)
	public Transactions getTransactionById(@Named("referenceCode") String referenceCode) throws NotFoundException {
		tDAO = new TransactionsDAO();
		Transactions transaction = tDAO.getTransactionsById(referenceCode);
		if (transaction != null) {
			return transaction;
		} else {
			throw new NotFoundException("Transaction doesn't exist.");
		}
	}

	@ApiMethod(name = "getPayHash", path = "transactionById/{payHash}", httpMethod = ApiMethod.HttpMethod.GET)
	public String getPayHash(@Named("referenceCode") String referenceCode) throws NotFoundException {
		tDAO = new TransactionsDAO();
		Transactions transaction = tDAO.getTransactionsById(referenceCode);
		if (transaction != null) {
			return transaction.getPayHash();
		} else {
			throw new NotFoundException("Transaction doesn't exist.");
		}
	}
	
	
	
}
