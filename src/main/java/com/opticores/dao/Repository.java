package com.opticores.dao;

import java.io.Serializable;

public interface Repository<E, Id> {

	public void addEntity(E entity);

	public void deleteEntity(E entity);

	public void updateEntity(E entity);

	public E findById(Serializable Id);

}
