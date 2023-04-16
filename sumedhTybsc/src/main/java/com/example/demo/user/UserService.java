package com.example.demo.user;


import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;
	

	public boolean isUserPresent(String username) {
		return userRepo.findByUsername(username) != null;
	}
	
	public void userAdd(String uString,String paString) {
			User user= new User();
			user.setUsername(uString);
			user.setPassword(paString);
			userRepo.save(user);		
	}
	
}
