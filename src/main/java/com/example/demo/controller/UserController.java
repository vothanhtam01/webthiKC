

package com.example.demo.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.NotesRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.entity.NotesEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.NotesSeviceImpl;
import com.example.demo.service.UserServiceImpl;

@Controller
public class UserController {
	
	@Autowired
	UserServiceImpl userServiceImpl;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	NotesSeviceImpl noteServiceImpl;

	@Autowired
	NotesRepository noteRepo;
	
	@Autowired
	NotesRepository  noteDao;
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("principal", false);
		model.addAttribute("listUser", userServiceImpl.findAll());
		return "home";
	}

	@GetMapping("/register")
	public String register(Model model) {
		UserEntity userEntity = new UserEntity();
		model.addAttribute("user", userEntity);
		return "register";
	}
	
	@PostMapping("/register")
	public String registerProccess(Model model, UserEntity user) {
		UserEntity userEntity = userServiceImpl.register(user);
		if(userEntity != null) {
			return "registerSuccess";
		} else {
			return "registerError";
		}
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		UserEntity userEntity = new UserEntity();
		model.addAttribute("user", userEntity);
		return "login";
	}
	
	@GetMapping("/note")
	public String take_notes(Model model, Principal principal) {
		if(principal ==  null) {
			return "403";
		}
		model.addAttribute("note", new NotesEntity());
	   return "note";
	}
	
	@PostMapping("/note")
	public String postNote(Model model, NotesEntity notesEntity, Principal principal) {
		noteServiceImpl.submit(notesEntity, userRepo.findByUsername(principal.getName()));
		return "note";
	}
	
	@GetMapping("/listNote")
	public String listNote(Model model) {
		model.addAttribute("listNote", noteRepo.findAll());
		return "listNote";
	}
    @GetMapping("/note/search")
   public String searchNote(Model model,@RequestParam(name = "Search") String search) {
    	model.addAttribute("listNote", noteDao.findByTitleContaining(search));
    	return "listNote";
    }
    @GetMapping("/note/filter")
    public String filterNote(Model model,@RequestParam(name="Filter") String filter) {
       model.addAttribute("listNote", noteDao.findByUser(userRepo.findByUsername(filter)));
        return "listNote";
    }
    
}
