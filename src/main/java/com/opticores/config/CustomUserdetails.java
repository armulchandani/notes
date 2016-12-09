package com.opticores.config;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * A custom user details class implementing the frameworks {@link UserDetails}
 * 
 * 
 * 
 * @author anubhav
 *
 */
public class CustomUserdetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7314700826565367787L;
	private Integer userId;
	private String username;
	private String password;

	public CustomUserdetails(Integer useridentifier, String username,
			String password) {
		userId = useridentifier;
		this.username = username;
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> granntedAuthorities = new java.util.ArrayList<SimpleGrantedAuthority>();
		granntedAuthorities.add(new SimpleGrantedAuthority("USER"));
		return granntedAuthorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return Boolean.TRUE;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return Boolean.TRUE;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return Boolean.TRUE;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return Boolean.TRUE;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
