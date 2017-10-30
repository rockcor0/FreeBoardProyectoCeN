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
		em = PersistenceManager.getEntityManager();
	}

	public boolean addCompany(Companies c) {

		try {
			em.getTransaction().begin();
			em.persist(c);
			em.getTransaction().commit();
			return true;
		} catch (Exception ex) {
		
			return false;
		}
	}

	public boolean updateCompanie(Companies c) {
		try {
			em.getTransaction().begin();
			em.merge(c);
			em.getTransaction().commit();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public Companies removeCompanie(String id) {
		Companies companie = null;
		try {
			companie = em.find(Companies.class, id);
			em.getTransaction().begin();
			em.remove(companie);
			em.getTransaction().commit();
			return companie;
		} catch (NoResultException e) {
			return companie;
		}
	}

	public List<Companies> getCompanies() {
		List<Companies> equipos = null;
		TypedQuery<Companies> q = em.createNamedQuery("Companies.findAll", Companies.class);
		try {
			equipos = q.getResultList();
		} catch (NoResultException e) {
			equipos = new ArrayList<Companies>();
		}

		return equipos;
	}

	public Companies getCompanyByEmail(String email) {
		Companies companie;
		try {
			companie = em.find(Companies.class, email);
		} catch (NoResultException e) {
			companie = null;
		}

		return companie;
	}

	public Companies getCompanieByName(String name) {
		Companies companie;
		try {
			TypedQuery<Companies> query = em.createNamedQuery("Companies.findByName", Companies.class);
			query.setParameter("name", name);
			companie = query.getSingleResult();
		} catch (NoResultException e) {
			companie = null;
		}
		return companie;
	}

}
