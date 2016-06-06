package com.exist.util;

import java.io.FileInputStream;
import java.util.Properties;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil{
    private static final SessionFactory sessionFactory;
    
    static {
        try{
            Configuration configuration = new Configuration().configure();
            StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
            .configure("hibernate.cfg.xml")
            .applySettings(configuration.getProperties())
            .build();

            Metadata metadata = new MetadataSources(standardRegistry)
            .getMetadataBuilder()
            .build();

            sessionFactory = metadata.getSessionFactoryBuilder().build();
        } 
        catch(Throwable ex){
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
    
    public static void closeSessionFactory(){
        sessionFactory.close();
    }
}
