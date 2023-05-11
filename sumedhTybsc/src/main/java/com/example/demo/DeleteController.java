package com.example.demo;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class DeleteController {
    private final PhotoService photoService;

    public DeleteController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping("/delete")
    public String getDelete(){
        return "delete";
    }
//    @PostMapping("/delete")
//    @ResponseBody
//    public RedirectView deleteFileById(@RequestParam("id") int id, RedirectAttributes redirectAttributes) {
//        // Your code to delete the file with the specified ID goes here
//            photoService.remove(id);
//            return new RedirectView("/delete");
//    }
    
    @PostMapping("/delete")
    public ModelAndView deleteFile(@RequestParam("id") String id,RedirectAttributes redirectAttributes,Model model) {
    	Photo photo=photoService.searchResult(id);
    	if(photo == null ) {
            return new ModelAndView("/error-404");
        }
    	
        photoService.remove(photo.getId());
        model.addAttribute("successMessage", "File Deleted successfully!");
        return new ModelAndView("/delete");
        
    }
    
    
    
    
    
}
