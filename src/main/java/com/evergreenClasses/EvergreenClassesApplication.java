package com.evergreenClasses;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.evergreenClasses.model.User;
import com.evergreenClasses.repository.UserRepository;

@SpringBootApplication
public class EvergreenClassesApplication {
		public static void main(String[] args) {
				SpringApplication.run(EvergreenClassesApplication.class, args);
		}

		// This bean will run on application startup and create a default user
    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (!userRepository.findByEmail("dumy@example.com").isPresent()) {
                User user = new User();
                user.setEmail("dumy@example.com");
                user.setPassword(passwordEncoder.encode("dumy123"));
                userRepository.save(user);
                System.out.println("Created default user: dumy@example.com with password: dumy123");
            }
        };
    }
	

}
