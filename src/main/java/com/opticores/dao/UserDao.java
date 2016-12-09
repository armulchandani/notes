package com.opticores.dao;

import com.opticores.model.User;

public interface UserDao {

	
	public User fetchUser(Integer id);
	
	public User fetchUserByLogin(String login);
	
	
}
