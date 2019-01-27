package com.cmc.springsecurity.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.cmc.springsecurity.entities.Role;
import com.cmc.springsecurity.entities.User;
import com.cmc.springsecurity.repository.RoleRepository;
import com.cmc.springsecurity.repository.UserRepository;

@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder ped;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (roleRepository.findByName("ROLE_ADMIN") == null) {
			roleRepository.save(new Role("ROLE_ADMIN"));
		}

		if (roleRepository.findByName("ROLE_MEMBER") == null) {
			roleRepository.save(new Role("ROLE_MEMBER"));
		}

		if (userRepository.findUserByEmail("admin@gmail.com") == null) {
			User user = new User();
			user.setEmail("admin@gmail.com");
			user.setPassword(ped.encode("123456"));
			Set<Role> roles = new HashSet<>();
			roles.add(roleRepository.findByName("ROLE_ADMIN"));
			roles.add(roleRepository.findByName("ROLE_MEMBER"));
			user.setRoles(roles);
			userRepository.save(user);
		}

		if (userRepository.findUserByEmail("member@gmail.com") == null) {
			User user = new User();
			user.setEmail("member@gmail.com");
			user.setPassword(ped.encode("123456"));
			Set<Role> roles = new HashSet<>();
			roles.add(roleRepository.findByName("ROLE_MEMBER"));
			userRepository.save(user);
		}
	}

}
