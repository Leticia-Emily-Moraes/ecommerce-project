package com.leticiamoraes.ecommerce.service;

import com.leticiamoraes.ecommerce.model.User;

import java.util.*;

public interface UserService {
	User save(User pUser);

	Optional<User> findById(Long pId);

	List<User> findAll();

	User update(Long pId, User pUser);

	User delete(Long pId);
}
