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

	public boolean addCompany(Companies c) {
		// Check for already exists
		try {
			em.getTransaction().begin();
			em.persist(c);
			em.flush();
			em.getTransaction().commit();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			em.close();
		}
	}

	public boolean updateCompanie(Companies c) {
		try {
			em.getTransaction().begin();
			em.merge(c); // cascades the tool & skill relationships
			em.flush();
			em.getTransaction().commit();
			return true;
		} catch (Exception ex) {
			return false;
		} finally {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			em.close();
		}
	}

	public boolean removeCompanie(String id) {
		try {
			Companies companie = getCompanieById(id);
			em.getTransaction().begin();
			em.remove(companie);
			em.flush();
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			em.close();
		}
	}

	public List<Companies> getCompanies() {
		List<Companies> equipos = null;
		em.getTransaction().begin();
		TypedQuery<Companies> q = em.createNamedQuery("Companies.findAll", Companies.class);
		try {
			equipos = q.getResultList();
			em.flush();
			em.getTransaction().commit();
		} catch (NoResultException e) {
			equipos = new ArrayList<Companies>();
		} finally {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			em.close();
		}

		return equipos;
	}

	public Companies getCompanieById(String id) {
		Companies companie;
		try {
			em.getTransaction().begin();
			companie = em.find(Companies.class, id);
			em.flush();
			em.getTransaction().commit();
		} catch (NoResultException e) {
			companie = null;
		} finally {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			em.close();
		}

		return companie;
	}

	public Companies getCompanieByName(String name) {
		Companies companie;
		try {
			em.getTransaction().begin();
			TypedQuery<Companies> query = em.createNamedQuery("Companies.findByName", Companies.class);
			query.setParameter("name", name);
			companie = query.getSingleResult();
			em.flush();
			em.getTransaction().commit();
		} catch (NoResultException e) {
			companie = null;
		} finally {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			em.close();
		}
		return companie;
	}

}
