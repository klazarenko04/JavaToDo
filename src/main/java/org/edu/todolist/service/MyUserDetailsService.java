package org.edu.todolist.service;

import org.edu.todolist.entity.UserCredentials;
import org.edu.todolist.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserCredentials user = userRepository.findByEmail(username);
				return new User(user.getEmail(),user.getPassword(),new ArrayList<>());
	}
	
	public String getUserNameFromPrincipal(String email)
	{
		UserCredentials user = userRepository.findByEmail(email);
		return (user.getFirstName()+" "+user.getLastName());
	}
}
