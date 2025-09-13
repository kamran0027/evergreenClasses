package com.evergreenClasses.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.evergreenClasses.Dto.LoginDto;
import com.evergreenClasses.Dto.UserDto;
import com.evergreenClasses.model.User;
import com.evergreenClasses.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //register user
    public void registerUser(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
    }

    // authenticate
    public Optional<User> authenticate(LoginDto loginDto) {
        Optional<User> userOpt = userRepository.findByEmail(loginDto.getEmail());
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    

}
