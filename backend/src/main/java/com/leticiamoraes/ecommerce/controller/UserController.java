package com.leticiamoraes.ecommerce.controller;

import com.leticiamoraes.ecommerce.model.User;
import com.leticiamoraes.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
	private final UserService userService;

	@Autowired
	public UserController(UserService pUserService){
		this.userService = pUserService;
	}

	@GetMapping
	public ResponseEntity<List<User>> findAll(){
		List<User> users = userService.findAll();
		return ResponseEntity.ok(users);
	}

	@GetMapping
	public ResponseEntity<User> findById(@RequestParam("id") Long pId) {
		return userService.findById(pId)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<User> create(@RequestBody User pUser) {
		User saved = userService.save(pUser);
		return ResponseEntity.ok(saved);
	}

	@PatchMapping
	public ResponseEntity<User> update(@RequestParam("id") Long pId, @RequestBody User pUser) {
		User updated = userService.update(pId, pUser);
		return ResponseEntity.ok(updated);
	}

	@DeleteMapping
	public ResponseEntity<User> delete(@RequestParam("id") Long pId) {
		User deleted = userService.delete(pId);
		return ResponseEntity.ok(deleted);
	}
}
