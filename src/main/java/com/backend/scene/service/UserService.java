package com.backend.scene.service;

import com.backend.scene.dto.UserDto;
import com.backend.scene.entity.SceneRequest.UpdateUserOutstandingBalanceRequest;
import com.backend.scene.entity.SceneResponse.UpdateUserOutstandingBalanceResponse;
import com.backend.scene.user.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User findById(Integer id);

    User findIdByEmail(String email);

    List<User> findAllUsers();

    Optional<User> findFromEmail(String email);

    UpdateUserOutstandingBalanceResponse chargeUser (UpdateUserOutstandingBalanceRequest request);

    UpdateUserOutstandingBalanceResponse userPaid (UpdateUserOutstandingBalanceRequest request);
}
