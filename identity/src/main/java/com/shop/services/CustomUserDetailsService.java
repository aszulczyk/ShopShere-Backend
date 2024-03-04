package com.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.shop.model.UserEntity;
import com.shop.repositories.UserEntityRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserEntityRepository userEntityRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userEntityRepository
				.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Could not find username: " + username));
		return userEntity;
	}
	
	

}
