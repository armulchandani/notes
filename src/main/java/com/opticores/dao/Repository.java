package com.opticores.dao;

import java.io.Serializable;

import com.opticores.exception.NoEntityFoundException;

public interface Repository<E, Id> {

	public void addEntity(E entity);

	public void deleteEntity(E entity);
	
	public void deleteEntityById(Serializable Id) throws NoEntityFoundException;

	public void updateEntity(E entity);

	public E findById(Serializable Id);

}
