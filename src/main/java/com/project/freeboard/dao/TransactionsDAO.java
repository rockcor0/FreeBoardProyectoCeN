package com.project.freeboard.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import com.project.freeboard.entity.Transactions;
import com.project.freeboard.service.PersistenceManager;

public class TransactionsDAO {
	
	private EntityManager em;
	
	public TransactionsDAO() {
		em = PersistenceManager.getEntityManager();
	}
	
	public boolean addTransactions(Transactions p) {
		
		try {
			em.getTransaction().begin();
			em.persist(p);
			em.flush();
			em.getTransaction().commit();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
	public boolean updateTransactions(Transactions p) {
		try {
			em.getTransaction().begin();
			em.merge(p); // cascades the tool & skill relationships
			em.flush();
			em.getTransaction().commit();
			em.close();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean removeTransactions(String id) {
		try {
			Transactions Transactions = em.find(Transactions.class, id);
			em.getTransaction().begin();
			em.remove(Transactions);
			em.flush();
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			em.close();
			return false;
		}
	}

	public List<Transactions> getTransactions() {
		List<Transactions> Transactions = null;
		em.getTransaction().begin();
		TypedQuery<Transactions> p = em.createNamedQuery("Equipo.getAll", Transactions.class);
		try {
			Transactions = p.getResultList();
		} catch (NoResultException e) {
			Transactions = new ArrayList<Transactions>();
		}

		em.flush();
		em.getTransaction().commit();
		return Transactions;
	}

	public Transactions getTransactionsById(String id) {

		em.getTransaction().begin();
		Transactions companie = em.find(Transactions.class, id);
		em.flush();
		em.getTransaction().commit();
		return companie;
	}
	
	public Transactions getTransactionsByBuyerEmail(String buyerEmail) {

		em.getTransaction().begin();
		Transactions Transactions = em.find(Transactions.class, buyerEmail);
		em.flush();
		em.getTransaction().commit();
		return Transactions;
	}

}
