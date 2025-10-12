package com.leticiamoraes.ecommerce.serviceImpl;

import com.leticiamoraes.ecommerce.model.User;
import com.leticiamoraes.ecommerce.repository.UserRepository;
import com.leticiamoraes.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public User save(User pUser) {
		if (pUser.getName() == null || pUser.getName().trim().isEmpty()) {
			throw new IllegalArgumentException("Name cannot be empty");
		}

		if (pUser.getEmail() == null || pUser.getEmail().trim().isEmpty()) {
			throw new IllegalArgumentException("Email cannot be empty");
		}

		if (userRepository.findByEmail(pUser.getEmail()).isPresent()) {
			throw new IllegalArgumentException("Email already exists");
		}

		pUser.setPassword(passwordEncoder.encode(pUser.getPassword()));

		pUser.setCreated_at(LocalDateTime.now());
		pUser.setUpdated_at(LocalDateTime.now());

		return userRepository.save(pUser);
	}

	@Override
	public Optional<User> findById(Long pId) {
		return userRepository.findById(pId);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User update(Long pId, User pUser) {
		return userRepository.findById(pId).map(existingUser -> {
			if (pUser.getName() != null && !pUser.getName().trim().isEmpty()) {
				existingUser.setName(pUser.getName());
			}
			if (pUser.getEmail() != null && !pUser.getEmail().equals(existingUser.getEmail())) {
				if (userRepository.findByEmail(pUser.getEmail()).isPresent()) {
					throw new IllegalArgumentException("Email already exists");
				}
				existingUser.setEmail(pUser.getEmail());
			}
			if (pUser.getPassword() != null && !pUser.getPassword().trim().isEmpty()) {
				existingUser.setPassword(passwordEncoder.encode(pUser.getPassword()));
			}
			if (pUser.getRole() != null) {
				existingUser.setRole(pUser.getRole());
			}
			existingUser.setUpdated_at(LocalDateTime.now());

			return userRepository.save(existingUser);
		}).orElseThrow(() -> new RuntimeException("User not found by id" + pId));
	}

	@Override
	public User delete(Long pId) {
		User user = userRepository.findById(pId).orElseThrow(() -> new IllegalArgumentException("User not found"));

		userRepository.delete(user);
		return user;
	}
}
