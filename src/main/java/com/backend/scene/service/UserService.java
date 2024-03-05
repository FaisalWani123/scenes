package com.backend.scene.service;

import com.backend.scene.dto.UserDto;
import com.backend.scene.user.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User findById(Integer id);

    List<User> findAllUsers();

    Optional<User> findFromEmail(String email);
}
