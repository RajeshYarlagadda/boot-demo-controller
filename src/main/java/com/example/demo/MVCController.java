package com.example.demo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;


@Controller
public class MVCController {

	
	private static final Logger log = LoggerFactory.getLogger(MVCController.class);

	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping("/userListing")
	public String viewUserListing(Model model) {
		
		List<User> userList = userRepository.findAll();
		log.info("userList "+userList);
		model.addAttribute("userList",userList);
		return "UserListing";		
	}
}
