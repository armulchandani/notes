package com.opticores.dao;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.opticores.exception.NoEntityFoundException;

/** An abstract implementation of {@link Repository}
 * 
 * 
 * @author anubhav
 *
 * @param <E>
 */
@SuppressWarnings("unchecked")
public class HibernateDAO<E> implements Repository<E, Serializable> {

	private Logger LOGGER= LoggerFactory.getLogger(HibernateDAO.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	private Class<E> persistentClass;

	public HibernateDAO(Class<E> entityClass) {

		persistentClass = entityClass;

	}

	@Override
	public void addEntity(E entity) {
		getSession().save(entity);

	}

	@Override
	public void deleteEntity(E entity) {
		getSession().delete(entity);

	}
	
	
	@Override
	public void deleteEntityById(Serializable Id) throws NoEntityFoundException {
		
		Session session= getSession();
		E entity= (E) session.get(persistentClass, Id);
		if(null!=entity){
			session.delete(entity);
		}
		else{
			LOGGER.info("Entity could not ne found with id '{}' ",Id);
			throw new NoEntityFoundException("Entity Not Found");
		}

	}

	@Override
	public void updateEntity(E entity) {
		getSession().saveOrUpdate(entity);

	}

	
	@Override
	public E findById(Serializable Id) {
		return (E) getSession().get(persistentClass, Id);
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

}
