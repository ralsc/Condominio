package config;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Rafael
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    
    static {
        try {
             Configuration configuration = new Configuration(); 
             configuration.configure("config/hibernate.cfg.xml"); 
             ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build(); 
             sessionFactory = configuration.buildSessionFactory(serviceRegistry); 
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Falha no session factoryy: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
