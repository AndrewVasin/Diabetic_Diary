package com.diary.service.impl;

import com.diary.model.User;
import com.diary.repository.UserRepository;
import com.diary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByLogin(login);
        if (user.isEmpty()) {
            throw  new UsernameNotFoundException("User ‘" + login + "’ not found");
        }
        return new org.springframework.security.core.userdetails.User(user.get().getLogin(),
                user.get().getPassword(),
                user.get().getAuthorities());
    }
}
