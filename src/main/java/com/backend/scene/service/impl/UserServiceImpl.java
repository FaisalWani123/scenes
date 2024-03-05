package com.backend.scene.service.impl;

import com.backend.scene.dto.UserDto;
import com.backend.scene.mapper.userMapper;
import com.backend.scene.repository.UserRepository;
import com.backend.scene.service.UserService;
import com.backend.scene.user.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).orElseThrow(null);

    }

    @Override
    public List<User> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users;

    }

    @Override
    public Optional<User> findFromEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

}
