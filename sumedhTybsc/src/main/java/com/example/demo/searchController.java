package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class searchController {
	    @Autowired
	    private PhotoService photoService;
	 
	    @GetMapping("/search")
	    public String getSearch(){
	        return "search";
	    }
	    
//	    @PostMapping("/search")
//	    public String searchPhoto(@RequestParam("id") String filename,Model model){
//	        Photo photo = photoService.searchResult(filename);
//	        if (photo == null) {
//	            model.addAttribute("photo", null);        	
//	        } else {
//	            model.addAttribute("photo", photo);
//	        }
//	        return "search";
//	    }

	    @PostMapping("/search")
	    public String searchPhoto(@RequestParam("id") String filename,Model model){
	        Photo photo = photoService.searchResult(filename);
	        if (photo == null) {
	            model.addAttribute("photo", null);        	
	        } else {
	            model.addAttribute("photo", photo);
	        }
	        return "search";
	    }
}
