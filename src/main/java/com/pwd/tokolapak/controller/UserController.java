package com.pwd.tokolapak.controller;

//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pwd.tokolapak.dao.UserRepo;
import com.pwd.tokolapak.entity.User;
import com.pwd.tokolapak.util.EmailUtil;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

	@Autowired
	private UserRepo userRepo;
	
	private PasswordEncoder pwEncoder = new BCryptPasswordEncoder();
	
	@Autowired 
	private EmailUtil emailUtil;
	
	@PostMapping
	public User registerUser(@RequestBody User user) {
		String encodedPassword = pwEncoder.encode(user.getPassword());
		
		user.setPassword(encodedPassword);
		
		User savedUser = userRepo.save(user);
		savedUser.setPassword(null);
		return savedUser;
	}
	
	
	//cara 1 login -> not recommended
	@PostMapping("/login")
	public User loginUser(@RequestBody User user) {
		User findUser = userRepo.findByUsername(user.getUsername()).get();
		
		if (pwEncoder.matches(user.getPassword(), findUser.getPassword())) {
			findUser.setPassword(null);
			return findUser;
		} 
		
		return null;
	}
	
	//cara 2 login --> recommend
	@GetMapping("/login")
	public User getLoginUser(@RequestParam String username, @RequestParam String password) {
		User findUser = userRepo.findByUsername(username).get();
		
		if (pwEncoder.matches(password, findUser.getPassword())) {
			findUser.setPassword(null);
			return findUser;
		} 
		
		return null;
	}
	
	@PostMapping("/sendEmail")
	public String sendEmailtesting() {
		this.emailUtil.sendEmail("ernandarn@gmail.com", "Coba Email SpringBoot", "Haloo, \n Apa Kabar?");
		return "Email Sent!";
	}
	
}
