package com.security.jwt;

import com.security.jwt.define.UserRole;
import com.security.jwt.entity.User;
import com.security.jwt.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;

@Slf4j
@SpringBootApplication
public class SpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@PostConstruct
	void initData() {
		User user = new User();
		user.setUsername("admin");
		user.setPassword(passwordEncoder.encode("admin"));
		user.setRole(UserRole.ADMIN.name());

		log.info("user: {}", user);
		userRepository.save(user);
	}
}
