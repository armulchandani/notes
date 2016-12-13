package com.opticores.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * This is a base entity class having only one attribute 'id'. Extended by
 * {@link Note} & {@link User}.
 * 
 * {link @MappedSuperClass} tells 'Hibernate' : hey hibernate the identifier for
 * the actual entities could be found inside this BaseEntity following a design
 * principle 'DRY'.
 * 
 * If {@link Note} & {@link User} don't extend from this class and no field
 * annotated with {@link @Id} is found , hibernate runtime will throw a runtime error 'No
 * identifier specified for entity'
 * 
 * 
 * 
 * @author anubhav
 *
 */
@MappedSuperclass
public class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
