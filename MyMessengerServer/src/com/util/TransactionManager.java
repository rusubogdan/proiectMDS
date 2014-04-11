package com.util;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class TransactionManager {
	private static ThreadLocal<Transaction> currentTransaction;
	
	public static Session getCurrentSession() {
		if(currentTransaction == null) {
			currentTransaction.set(HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction());
		}
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}
	
	public static void rollback() {
		currentTransaction.get().rollback();
		HibernateUtil.getSessionFactory().getCurrentSession().close();
		currentTransaction = null;
	}
	
	public static void commit() {
		currentTransaction.get().commit();
		HibernateUtil.getSessionFactory().getCurrentSession().close();
		currentTransaction = null;
	}
	
}
