package com.backend.scene.service.impl;

import com.backend.scene.dto.UserDto;
import com.backend.scene.entity.SceneRequest.UpdateUserOutstandingBalanceRequest;
import com.backend.scene.entity.SceneResponse.UpdateUserOutstandingBalanceResponse;
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

    private Integer findOutStandingAmount(User user){
        Integer outstandingAmount = user.getOutstandingAmount();
        if (outstandingAmount == null){
            return 0;
        }
        return outstandingAmount;
    }

    private UpdateUserOutstandingBalanceResponse UpdateUserOutstandingBalanceResponseError (String errormsg){
        return UpdateUserOutstandingBalanceResponse.builder()
                .errorMsg(errormsg)
                .confirmed(false)
                .build();
    }

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).orElseThrow(null);

    }

    @Override
    public User findIdByEmail(String email) {
        User foundUser = userRepository.findByEmail(email).orElse(null);
        if (foundUser == null){
            return null;
        }
        return foundUser;
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

    @Override
    public UpdateUserOutstandingBalanceResponse chargeUser(UpdateUserOutstandingBalanceRequest request) {
        User user = userRepository.findById(request.getUserId()).orElse(null);
        if (user == null){
            return this.UpdateUserOutstandingBalanceResponseError("User not found");
        }
        Integer outstandingAmount = this.findOutStandingAmount(user);
        user.setOutstandingAmount(outstandingAmount + request.getAmount());
        userRepository.save(user);
        return UpdateUserOutstandingBalanceResponse.builder()
                .userId(user.getId())
                .amount(request.getAmount())
                .sceneId(request.getSceneId())
                .confirmed(true)
                .build();
    }

    @Override
    public UpdateUserOutstandingBalanceResponse userPaid(UpdateUserOutstandingBalanceRequest request) {
        User user = userRepository.findById(request.getUserId()).orElse(null);
        if (user == null){
            return this.UpdateUserOutstandingBalanceResponseError("User not found");
        }
        Integer outstandingAmount = this.findOutStandingAmount(user);
        if (outstandingAmount == null){
            user.setOutstandingAmount(0);
        }
        if (outstandingAmount < request.getAmount()){
            return this.UpdateUserOutstandingBalanceResponseError("User is being over charged");
        }
        user.setOutstandingAmount(outstandingAmount - request.getAmount());
        userRepository.save(user);
        return UpdateUserOutstandingBalanceResponse.builder()
                .userId(user.getId())
                .amount(request.getAmount())
                .sceneId(request.getSceneId())
                .confirmed(true)
                .build();
    }

}
