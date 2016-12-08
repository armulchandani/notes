package com.opticores.dao;

import java.io.Serializable;

import com.opticores.exception.NoEntityFoundException;

/** An interface exposing basic CRUD operations using generic
 * 
 * @author anubhav
 *
 * @param <E>
 * @param <Id>
 */
public interface Repository<E, Id> {

	public void addEntity(E entity);

	public void deleteEntity(E entity);
	
	public void deleteEntityById(Serializable Id) throws NoEntityFoundException;

	public void updateEntity(E entity);

	public E findById(Serializable Id);

}
