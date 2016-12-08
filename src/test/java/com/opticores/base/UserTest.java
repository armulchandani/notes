package com.opticores.base;

import org.springframework.beans.factory.annotation.Autowired;

import com.opticores.dao.UserDao;
import com.opticores.model.User;
import static org.junit.Assert.*;

public class UserTest extends BaseTest {
	
	
	@Autowired
	private UserDao userDao;
	
	public void fetchUserByIdShouldPass(){
		
		User user=userDao.fetchUser(1);
		assertNotNull(user);
		
	}

}
