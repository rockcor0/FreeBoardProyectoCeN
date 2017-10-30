package com.project.freeboard.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.project.freeboard.entity.Offers;
import com.project.freeboard.service.PersistenceManager;

public class OffersDAO {

	private EntityManager em;

	public OffersDAO() {
		em = PersistenceManager.getEntityManager();
	}

	public boolean addOffers(Offers o) {
		// Check for already exists
		try {
			em.getTransaction().begin();
			em.persist(o);
			em.flush();
			em.getTransaction().commit();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean updateOffers(Offers o) {
		try {
			em.getTransaction().begin();
			em.merge(o); // cascades the tool & skill relationships
			em.flush();
			em.getTransaction().commit();
			em.close();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean removeOffers(String id) {
		try {
			Offers Offers = em.find(Offers.class, id);
			em.getTransaction().begin();
			em.remove(Offers);
			em.flush();
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			em.close();
			return false;
		}
	}

	public List<Offers> getOffers() {
		List<Offers> Offerss = null;
		em.getTransaction().begin();
		TypedQuery<Offers> q = em.createNamedQuery("Offers.findAll", Offers.class);
		try {
			Offerss = q.getResultList();
		} catch (NoResultException e) {
			Offerss = new ArrayList<Offers>();
		}

		em.flush();
		em.getTransaction().commit();
		return Offerss;
	}

	public List<Offers> getOfferssByStudent(String id) {
		List<Offers> Offerss = null;
		em.getTransaction().begin();
		TypedQuery<Offers> q = em.createNamedQuery("Offers.getOffersByStudent", Offers.class);
		q.setParameter("students_cc", id);
		try {
			Offerss = q.getResultList();
		} catch (NoResultException e) {
			Offerss = new ArrayList<Offers>();
		}

		em.flush();
		em.getTransaction().commit();
		return Offerss;
	}

	public List<Offers> getOfferssByAuction(String id) {
		List<Offers> Offerss = null;
		em.getTransaction().begin();
		TypedQuery<Offers> q = em.createNamedQuery("Offers.getOffersByAuction", Offers.class);
		q.setParameter("auctions_idauctions", id);
		try {
			Offerss = q.getResultList();
		} catch (NoResultException e) {
			Offerss = new ArrayList<Offers>();
		}

		em.flush();
		em.getTransaction().commit();
		return Offerss;
	}

	public Offers getOffersById(String id) {

		em.getTransaction().begin();
		Offers Offers = em.find(Offers.class, id);
		em.flush();
		em.getTransaction().commit();
		return Offers;
	}

}
