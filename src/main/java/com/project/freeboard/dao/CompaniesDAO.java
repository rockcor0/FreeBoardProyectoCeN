package com.project.freeboard.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.project.freeboard.entity.Companies;
import com.project.freeboard.service.PersistenceManager;

public class CompaniesDAO {

	private EntityManager em;

	public CompaniesDAO() {
		em = PersistenceManager.get().createEntityManager();
	}

	public boolean addCompany(Companies e) {
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

	public boolean updateCompanie(Companies c) {
		try {
			em.getTransaction().begin();
			em.merge(c); // cascades the tool & skill relationships
			em.flush();
			em.getTransaction().commit();
			em.close();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean removeCompanie(String id) {
		try {
			Companies companie = em.find(Companies.class, id);
			em.getTransaction().begin();
			em.remove(companie);
			em.flush();
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			em.close();
			return false;
		}
	}

	public List<Companies> getCompanies() {
		List<Companies> equipos = null;
		em.getTransaction().begin();
		TypedQuery<Companies> q = em.createNamedQuery("Companies.getAll", Companies.class);
		try {
			equipos = q.getResultList();
		} catch (NoResultException e) {
			equipos = new ArrayList<Companies>();
		}

		em.flush();
		em.getTransaction().commit();
		return equipos;
	}

	public Companies getCompanieById(String id) {

		em.getTransaction().begin();
		Companies companie = em.find(Companies.class, id);
		em.flush();
		em.getTransaction().commit();
		return companie;
	}
	
	public Companies getCompanieByName(String name) {

		em.getTransaction().begin();
		Companies companie = null;
		TypedQuery<Companies> query = em.createNamedQuery("Companies.findByName", Companies.class);
		query.setParameter("name", name);
		companie=query.getSingleResult();
		em.flush();
		em.getTransaction().commit();
		return companie;
	}
	
	
}
