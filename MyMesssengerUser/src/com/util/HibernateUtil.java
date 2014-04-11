package com.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.entities.Friend;
import com.entities.FriendPK;
import com.entities.User;

public class HibernateUtil {
	
	private static final SessionFactory SESSION_FACTORY;
	
	static {
		Configuration configuration = new Configuration().configure();
		configuration.addAnnotatedClass(User.class);
		configuration.addAnnotatedClass(Friend.class);
		configuration.addAnnotatedClass(FriendPK.class);
		StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
	    serviceRegistryBuilder.applySettings(configuration.getProperties());
	    ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
	    SESSION_FACTORY = configuration.buildSessionFactory(serviceRegistry);
	}
	
	public static SessionFactory getSessionFactory() {
		return SESSION_FACTORY;
	}
}