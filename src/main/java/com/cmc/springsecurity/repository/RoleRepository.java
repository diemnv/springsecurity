package com.cmc.springsecurity.repository;

import org.springframework.data.repository.CrudRepository;

import com.cmc.springsecurity.entities.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {
	Role findByName(String name);
}
