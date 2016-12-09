package com.opticores.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.opticores.model.User;

@Repository
public class UserDaoImpl extends HibernateDAO<User> implements UserDao {

	public UserDaoImpl() {
		super(User.class);
	}

	@Override
	public User fetchUser(Integer id) {
		
		return findById(id);
	}

	@Override
	public User fetchUserByLogin(String login) {
		
		Query query= getSession().getNamedQuery("fetchUserByLoginId");
		query.setParameter("user", login);
		
		User user= (User) query.uniqueResult();
		
		return user;
	}

}
