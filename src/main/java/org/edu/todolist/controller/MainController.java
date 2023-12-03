package org.edu.todolist.controller;


import org.edu.todolist.dto.LoginRequestData;
import org.edu.todolist.entity.ToDoItems;
import org.edu.todolist.entity.UserCredentials;
import org.edu.todolist.repo.FileUserRepository;
import org.edu.todolist.repo.ToDoItemsRepository;
import org.edu.todolist.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MainController {
	
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	@Autowired
	FileUserRepository userRepository;
	@Autowired
	ToDoItemsRepository todoitemrepo;
	
	@Autowired
    AuthenticationManager authenticationManager;
	@Autowired
	MyUserDetailsService userDetailsService;
	
	@RequestMapping("/")
	public String Home(ModelMap modelMap)
	{
		String useremail=SecurityContextHolder.getContext().getAuthentication().getName();
		List<ToDoItems> items=todoitemrepo.findByUserName(useremail);
		modelMap.addAttribute("userName", userDetailsService.getUserNameFromPrincipal(useremail));
		modelMap.addAttribute("items", items);
		return "home";
	}
	
	@RequestMapping("/login")
	public String loginPage()
	{
		return "loginpage";
	}
	
	@PostMapping("/login")
	public String loginUser(LoginRequestData request)
	{
		try {
			UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
			Authentication auth=authenticationManager.authenticate(token);
			SecurityContext sc=SecurityContextHolder.getContext();
			sc.setAuthentication(auth);
		} catch (AuthenticationException e) {
			
			
		}
		
		
		return "redirect:/";
	}
	
	@RequestMapping("/register")
	public String showRegister() {
	

		return "registerpage";
	}
	
	@PostMapping("/register")
	public String registerUser(UserCredentials userReceived) {
		String pass = userReceived.getPassword();
		userReceived.setPassword(passwordEncoder.encode(pass));

		// Замість userRepository.save(userReceived);
		userRepository.save(userReceived); // Додайте метод збереження в файл

		return "redirect:/login";
	}
}
