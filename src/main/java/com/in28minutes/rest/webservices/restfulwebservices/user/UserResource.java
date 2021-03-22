package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.in28minutes.rest.webservices.restfulwebservices.post.Post;



@RestController
public class UserResource {
	
	@Autowired
	private UserDaoService service;
	// get all users
	//hello-world-bean
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}
	
	@GetMapping("/users/{id}")
	public User retrieveUser(@PathVariable int id) {
		User savedUser = service.findOne(id);
		if (savedUser == null) {
			throw new UserNotFoundException("id-" + id);
		}
		return savedUser; 
		
	}
	
	@GetMapping("/users/{id}/posts")
	public List<Post> retrieveUserPosts(@PathVariable int id) {
		User savedUser = service.findOne(id);
		if (savedUser == null) {
			throw new UserNotFoundException("id-" + id);
		}
		return savedUser.getPosts(); 
		
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		User savedUser = service.save(user);
		
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
		
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User savedUser = service.deleteById(id);
		if (savedUser == null) {
			throw new UserNotFoundException("id-" + id);
		}
	}
	
	
	@PostMapping("/users/{id}/posts")
	public ResponseEntity<Object> createUserPost(@PathVariable int id, @RequestBody Post post) {
		User savedUser = service.findOne(id);
		if (savedUser == null) {
			throw new UserNotFoundException("id-" + id);
		}
		
		savedUser.addPost(post);
		
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{post_id}")
			.buildAndExpand(post.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}
	
	
}

