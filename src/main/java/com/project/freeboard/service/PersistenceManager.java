package com.project.freeboard.service;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.google.appengine.api.utils.SystemProperty;

public final class PersistenceManager {

	private static EntityManagerFactory entityManagerFactory = null;

	public static EntityManager getEntityManager() {

		if (entityManagerFactory == null)
			entityManagerFactory = Persistence.createEntityManagerFactory("freeboard");

		return entityManagerFactory.createEntityManager();
	}

}