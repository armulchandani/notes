package com.opticores.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.opticores.model.User;
import com.opticores.service.UserService;

/**
 * A custom but a basic user details service class responsible for loading the
 * user details required from data source
 * 
 * Required by spring security framework
 * 
 * 
 * 
 * @author anubhav
 *
 */
@Component(value = "basicUserDetailsService")
public class BasicUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		final User user = userService.getUserByLogin(username);
		if (null == user) {
			throw new UsernameNotFoundException(username + " not found");

		}

		UserDetails userDetails = new CustomUserdetails(user.getId(),
				user.getEmail(), user.getPassword());

		return userDetails;
	}

}
