package com.souvik.fresh_votes.service;

import com.souvik.fresh_votes.domain.User;
import com.souvik.fresh_votes.repository.UserRepository;
import com.souvik.fresh_votes.security.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User save(User user) {

        String encodedPassword = passwordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);

        final Authority authority = new Authority();
        authority.setAuthority("ROLE_USER");
        authority.setUser(user);

        user.getAuthorities().add(authority);

        userRepository.save(user);
        return user;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
