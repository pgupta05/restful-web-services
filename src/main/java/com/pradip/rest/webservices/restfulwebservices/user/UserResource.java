package com.pradip.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {
	@Autowired
	private UserDaoService service;
	//Get /user
	//retriveAllUsers
	
	@GetMapping("/users")
	public List<User> retriveAllUsers() {
		return service.findAll();
	}
	  
	//Get /user/{id}
	//retriveUserbyId
	@GetMapping("/users/{id}")
	public Resource<User> retriveUser(@PathVariable int id) {
		User findUser = service.findOne(id);
		if (findUser==null) {
			throw new UserNotFoundException("No user found for id = "+id);
		}
		
		//"all-users", SERVER_PATH + "/users"
		//retriveAllUsers
		//HATEOAS
		
		Resource<User> resource = new Resource<User>(findUser);
		
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retriveAllUsers());
		resource.add(linkTo.withRel("all-users"));
		return resource;
		
	}
	

	
	@PostMapping("/users")
	public  ResponseEntity<Object> CreateUsers(@Valid @RequestBody User user) {
		User savedUser = service.save(user);
		
		// CREATED
		// /user/{id}   saveduser.GetId()
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	  
	//Delete /user/{id}
	//DeleteUserbyId
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User deleteUser = service.deleteById(id);
		if (deleteUser==null) {
			throw new UserNotFoundException("No user found for id = "+id);
		}
		
		
	}
	
	
	
}
