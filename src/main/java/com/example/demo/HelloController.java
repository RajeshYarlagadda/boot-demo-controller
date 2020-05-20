package com.example.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;



@RestController
public class HelloController {
	@RequestMapping("/api/hello")
	public String greet() {
		return "Hello from otherside";
	}

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/getUsers")
	public List<User> getUser(){
		return userRepository.findAll();				
	}
	
	@GetMapping("/getUser/{id}")
	public User getUserById(@PathVariable("id") Long id) {
		System.out.println("id "+id);
		return userRepository.getOne(id);
	}
	
	@PostMapping("/createUser")
	public User createUser(@Valid @RequestBody User user) {
		
		System.out.println(user);
		return userRepository.save(user);

	}
	
	@PutMapping("/userUpdate/{id}")
	public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId,
			@Valid @RequestBody User employeeDetails)  {
		User employee = userRepository.getOne(userId);

		employee.setEmail(employeeDetails.getEmail());
		employee.setLastName(employeeDetails.getLastName());
		employee.setFirstName(employeeDetails.getFirstName());
		final User updatedEmployee = userRepository.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}

	@DeleteMapping("/userDelete/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
			 {
		User employee = userRepository.getOne(employeeId);				

		userRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
