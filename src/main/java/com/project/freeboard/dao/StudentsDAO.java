package com.project.freeboard.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.project.freeboard.entity.Students;
import com.project.freeboard.service.PersistenceManager;

public class StudentsDAO {

	private EntityManager em;

	public StudentsDAO() {
		em = PersistenceManager.getEntityManager();
	}

	public boolean addStudent(Students s) {
		try {
			em.getTransaction().begin();
			em.persist(s);
			em.getTransaction().commit();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public boolean updateStudent(Students s) {
		try {
			em.getTransaction().begin();
			em.merge(s);
			em.getTransaction().commit();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public Students removeStudent(String id) {
		Students s = null;
		try {
			s = em.find(Students.class, id);
			em.getTransaction().begin();
			em.remove(s);
			em.getTransaction().commit();
			return s;
		} catch (Exception e) {
			return s;
		}
	}

	public List<Students> getStudents() {
		List<Students> students = null;
		TypedQuery<Students> q = em.createNamedQuery("Students.getAll", Students.class);
		try {
			students = q.getResultList();
		} catch (NoResultException e) {
			students = new ArrayList<Students>();
		}
		return students;
	}

	public Students getStudentByEmail(String email) {

		Students student = em.find(Students.class, email);
		return student;
	}

}