package com.project.freeboard.service;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.google.appengine.api.utils.SystemProperty;

public final class PersistenceManager {

    private static final EntityManagerFactory emfInstance =
            Persistence.createEntityManagerFactory("freeboard", getEntityManagerProperties());


    public static EntityManagerFactory get() {
        return emfInstance;
    }

    private static Map<String, String> getEntityManagerProperties()
    {
        Map<String, String> properties = new HashMap();
        if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
            properties.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.GoogleDriver");
            properties.put("javax.persistence.jdbc.url", System.getProperty("cloudsql.url"));
        } else {
            properties.put("javax.persistence.jdbc.driver","com.mysql.jdbc.Driver");
            properties.put("javax.persistence.jdbc.url",System.getProperty("cloudsql.url.dev"));
        }
        return properties;
    }
	
}