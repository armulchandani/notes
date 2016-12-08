package com.opticores.service;

import com.opticores.model.User;

/**
 * A simple interface exposing a contract for retrieving the user by it's
 * identifier
 * 
 * 
 * @author anubhav
 *
 */
public interface UserService {

	/**
	 * Function basically responsible for retrieving the user provide it's
	 * identifier
	 * 
	 * @param id
	 * @return an instance of user object or null
	 */
	public User getUserById(Integer id);

}
