package com.project.freeboard.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.project.freeboard.entity.Payment;
import com.project.freeboard.service.PersistenceManager;

public class PaymentDAO {
	
	private EntityManager em;
	
	public PaymentDAO() {
		em = PersistenceManager.get().createEntityManager();
	}
	
	public boolean addPayment(Payment p) {
		
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
	
	public boolean updatePayment(Payment p) {
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

	public boolean removePayment(String id) {
		try {
			Payment payment = em.find(Payment.class, id);
			em.getTransaction().begin();
			em.remove(payment);
			em.flush();
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			em.close();
			return false;
		}
	}

	public List<Payment> getPayment() {
		List<Payment> payment = null;
		em.getTransaction().begin();
		TypedQuery<Payment> p = em.createNamedQuery("Equipo.getAll", Payment.class);
		try {
			payment = p.getResultList();
		} catch (NoResultException e) {
			payment = new ArrayList<Payment>();
		}

		em.flush();
		em.getTransaction().commit();
		return payment;
	}

	public Payment getPaymentById(String id) {

		em.getTransaction().begin();
		Payment companie = em.find(Payment.class, id);
		em.flush();
		em.getTransaction().commit();
		return companie;
	}
	
	public Payment getPaymentByBuyerEmail(String buyerEmail) {

		em.getTransaction().begin();
		Payment payment = em.find(Payment.class, buyerEmail);
		em.flush();
		em.getTransaction().commit();
		return payment;
	}

}
