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
		em = PersistenceManager.get().createEntityManager();
	}

	public boolean addStudent(Students s) {
		// Check for already exists
		try {
			em.getTransaction().begin();
			em.persist(s);
			em.flush();
			em.getTransaction().commit();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean updateStudent(Students s) {
		try {
			em.getTransaction().begin();
			em.merge(s); // cascades the tool & skill relationships
			em.flush();
			em.getTransaction().commit();
			em.close();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean removeStudent(String id) {
		try {
			Students student = em.find(Students.class, id);
			em.getTransaction().begin();
			em.remove(student);
			em.flush();
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			em.close();
			return false;
		}
	}

	public List<Students> getStudents() {
		List<Students> students = null;
		em.getTransaction().begin();
		TypedQuery<Students> q = em.createNamedQuery("Students.getAll", Students.class);
		try {
			students = q.getResultList();
		} catch (NoResultException e) {
			students = new ArrayList<Students>();
		}

		em.flush();
		em.getTransaction().commit();
		return students;
	}

	public Students getStudentByCC(String cc) {

		em.getTransaction().begin();
		Students student = em.find(Students.class, cc);
		em.flush();
		em.getTransaction().commit();
		return student;
	}

}