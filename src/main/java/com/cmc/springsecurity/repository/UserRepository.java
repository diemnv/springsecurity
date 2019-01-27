package com.cmc.springsecurity.repository;

import org.springframework.data.repository.CrudRepository;

import com.cmc.springsecurity.entities.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	User findUserByEmail(String email);
}
