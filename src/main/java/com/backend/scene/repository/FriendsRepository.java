package com.backend.scene.repository;

import com.backend.scene.entity.Friends;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendsRepository extends JpaRepository<Friends, Integer> {

    List<Friends> findAllFriendsByUserId(Integer userId);

}
