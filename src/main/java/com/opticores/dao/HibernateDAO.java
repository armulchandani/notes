package com.opticores.dao;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author anubhav
 *
 * @param <E>
 */
public abstract class HibernateDAO<E> implements Repository<E, Serializable> {

	@Autowired
	private SessionFactory sessionFactory;

	private Class<E> persistenceClass;

	public HibernateDAO(Class<E> entityClass) {

		persistenceClass = entityClass;

	}

	@Override
	public void addEntity(E entity) {
		getSession().save(entity);

	}

	@Override
	public void deleteEntity(E entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateEntity(E entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public E findById(Serializable Id) {
		return (E) getSession().get(persistenceClass, Id);
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

}
