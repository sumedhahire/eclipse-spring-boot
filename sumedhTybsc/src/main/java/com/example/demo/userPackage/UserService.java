package com.example.demo.userPackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private UserRepo userRepo;
	
	 @Autowired
	 private BCryptPasswordEncoder passwordEncoder;
	
	public boolean isUserPresent(String username) {
		return userRepo.findByUsername(username) != null;
	}
	
	public boolean getUser(String uString, String paString) {
	    User user = userRepo.findByUsername("sagar");
	    if (user != null) { 
	        String encodedPassword = user.getPassword();
	        if (passwordEncoder.matches(paString, encodedPassword)) { 
	            return true;
	        } else {
	            System.out.println("*********password fail********");
	            return false;
	        }
	    } else {
	        System.out.println("*********user not found********");
	        return false;
	    }
	}


	
	public void userAdd(String uString,String paString) {
		   String encodedPassword = passwordEncoder.encode(paString);
			User user= new User();
			user.setUsername(uString);
			user.setPassword(encodedPassword);
			userRepo.save(user);		
	}
	

	
}
