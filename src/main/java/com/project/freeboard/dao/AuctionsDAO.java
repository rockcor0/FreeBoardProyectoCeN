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
		em = PersistenceManager.get().createEntityManager();
	}

	public boolean addAuctions(Auctions e) {
		// Check for already exists
		try {
			em.getTransaction().begin();
			em.persist(e);
			em.flush();
			em.getTransaction().commit();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean updateAuctions(Auctions e) {
		try {
			em.getTransaction().begin();
			em.merge(e); // cascades the tool & skill relationships
			em.flush();
			em.getTransaction().commit();
			em.close();
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
			em.flush();
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			em.close();
			return false;
		}
	}

	public List<Auctions> getAuctions() {
		List<Auctions> equipos = null;
		em.getTransaction().begin();
		TypedQuery<Auctions> q = em.createNamedQuery("Auctions.getAll", Auctions.class);
		try {
			equipos = q.getResultList();
		} catch (NoResultException e) {
			equipos = new ArrayList<Auctions>();
		}

		em.flush();
		em.getTransaction().commit();
		return equipos;
	}

	public Auctions getAuctionsById(String id) {

		em.getTransaction().begin();
		Auctions auction = em.find(Auctions.class, id);
		em.flush();
		em.getTransaction().commit();
		return auction;
	}

	public Auctions getAuctionsByType(String type) {

		em.getTransaction().begin();
		Auctions auction = null;
		TypedQuery<Auctions> query = em.createNamedQuery("Auctions.findByType", Auctions.class);
		query.setParameter("type", type);
		auction = query.getSingleResult();
		em.flush();
		em.getTransaction().commit();
		return auction;
	}
	
	public Auctions getAuctionsByName(String name) {

		em.getTransaction().begin();
		Auctions auction = null;
		TypedQuery<Auctions> query = em.createNamedQuery("Auctions.findByName", Auctions.class);
		query.setParameter("name", name);
		auction = query.getSingleResult();
		em.flush();
		em.getTransaction().commit();
		return auction;
	}

	public Auctions getAuctionsByTime(String time) {

		em.getTransaction().begin();
		Auctions auction = null;
		TypedQuery<Auctions> query = em.createNamedQuery("Auctions.findByTime", Auctions.class);
		query.setParameter("time", time);
		auction = query.getSingleResult();
		em.flush();
		em.getTransaction().commit();
		return auction;
	}

	public Auctions getAuctionsByPrice(String price) {

		em.getTransaction().begin();
		Auctions auction = null;
		TypedQuery<Auctions> query = em.createNamedQuery("Auctions.findByPrice", Auctions.class);
		query.setParameter("price", price);
		auction = query.getSingleResult();
		em.flush();
		em.getTransaction().commit();
		return auction;
	}

}
