package com.swiggy.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.swiggy.model.User;
import com.swiggy.repository.UserRepository;

@Service
public class ManualUserDetailService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username).orElseThrow(()->new  UsernameNotFoundException("can't find user with username "+username));
		
		List<GrantedAuthority> auths = new ArrayList<>();
		SimpleGrantedAuthority autho = new SimpleGrantedAuthority("ROLE_"+user.getRole().toUpperCase());
		org.springframework.security.core.userdetails.User secUser = new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),null);
		auths.add(autho);
		return secUser;
	}

}
