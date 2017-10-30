package com.project.freeboard.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.project.freeboard.entity.Auctions;
import com.project.freeboard.service.PersistenceManager;

public class AuctionsDAO {

	private EntityManager em;

	public AuctionsDAO() {
		em = PersistenceManager.getEntityManager();
	}

	public boolean addAuctions(Auctions e) {
		// Check for already exists
		try {
			em.getTransaction().begin();
			em.persist(e);
			em.getTransaction().commit();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public boolean updateAuctions(Auctions e) {
		try {
			em.getTransaction().begin();
			em.merge(e);
			em.getTransaction().commit();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean removeAuctions(String id) {
		try {
			Auctions equipo = em.find(Auctions.class, id);
			em.getTransaction().begin();
			em.remove(equipo);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public List<Auctions> getAuctions() {
		List<Auctions> equipos = null;
		TypedQuery<Auctions> q = em.createNamedQuery("Auctions.findAll", Auctions.class);
		try {
			equipos = q.getResultList();
		} catch (NoResultException e) {
			equipos = new ArrayList<Auctions>();
		}

		return equipos;
	}

	public Auctions getAuctionsById(String id) {
		Auctions auction = em.find(Auctions.class, id);
		return auction;
	}

	public Auctions getAuctionsByType(String type) {

		Auctions auction = null;
		TypedQuery<Auctions> query = em.createNamedQuery("Auctions.findByType", Auctions.class);
		query.setParameter("type", type);
		auction = query.getSingleResult();
		return auction;
	}

	public Auctions getAuctionsByTime(String time) {

		Auctions auction = null;
		TypedQuery<Auctions> query = em.createNamedQuery("Auctions.findByTime", Auctions.class);
		query.setParameter("time", time);
		auction = query.getSingleResult();
		return auction;
	}

	public Auctions getAuctionsByPrice(String price) {

		Auctions auction = null;
		TypedQuery<Auctions> query = em.createNamedQuery("Auctions.findByPrice", Auctions.class);
		query.setParameter("price", price);
		auction = query.getSingleResult();
		return auction;
	}

}
