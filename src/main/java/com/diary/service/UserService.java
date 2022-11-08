package com.diary.service;

import com.diary.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<User> getAllUsers(Pageable pageable);
    User getUserById(Integer id);

    void saveUser(User user);
}
