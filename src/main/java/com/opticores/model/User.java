package com.opticores.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;

@Entity
@NamedNativeQueries({ @NamedNativeQuery(name = "fetchUserByLoginId", query = "select * from user u where u.email=:user", resultClass = User.class) })
public class User extends BaseEntity implements Serializable{
	
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 77465780931697871L;
	

	@Column
	private String email;
	
	@Column
	private String password;
	
	@Column
	private Timestamp created;
	
	@Column
	private Timestamp updated;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="user")
	private Set<Note> notes;
	
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Timestamp getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	public Timestamp getUpdated() {
		return updated;
	}
	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}
	
	

}
