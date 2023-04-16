package com.example.demo;

import java.io.IOException;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class FileController {

	private final PhotoService photoservice;

    public FileController(PhotoService photoservice) {
		this.photoservice=photoservice;
	}
    
    @GetMapping("/home")
    public String getHome(){
        return "getHomeView";
    }
    
    @GetMapping("/")
    public ModelAndView getDefualt() {
    	return new ModelAndView(getHome());
    }
    
    @GetMapping("/upload")
    public String getUpload(){
        return "upload";
    }
	
    @PostMapping("/upload")
    @ResponseBody
    public ModelAndView create(@RequestParam("file") MultipartFile file, Model model, RedirectAttributes redirectAttrs) throws IOException {
    	//Photo photo= photoservice.put(file.getOriginalFilename(),file.getContentType(),file.getBytes());
    	if(file.getContentType()==null) {
    		return new ModelAndView("/error-404");
    	}else if (photoservice.searchResult(file.getOriginalFilename()) != null){
    		model.addAttribute("successMessage", "File with same name has found,please consider changing the file name");
    		return new ModelAndView("/upload");
		}
    	else {
    		Photo photo= photoservice.put(file.getOriginalFilename(),file.getContentType(),file.getBytes());
    		model.addAttribute("successMessage", "File uploaded successfully!");
    		return new ModelAndView("/upload");
    	}
    }
    @GetMapping("/photo")
    public String get(Model model){
        Iterable<Photo> photos = photoservice.get();
        model.addAttribute("photos", photos);
        return "photo";
    }
}
