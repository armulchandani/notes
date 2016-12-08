package com.opticores.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opticores.dao.UserDao;
import com.opticores.model.User;

/** Concrete implementation of interface {@link UserService}}
 * 
 * @author anubhav
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	/** 
	 * {@inheritDoc}}
	 */
	@Override
	public User getUserById(Integer id) {
		
		return userDao.fetchUser(id);
	}

}
