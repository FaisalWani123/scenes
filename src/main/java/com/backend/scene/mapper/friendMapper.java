package com.backend.scene.mapper;

import com.backend.scene.dto.FriendDto;
import com.backend.scene.entity.Friends;

public class friendMapper {
    public static Friends mapToFriend(FriendDto friendDto){
        return Friends.builder()
                .friendship_id(friendDto.getFriendship_id())
                .userId(friendDto.getUserId())
                .userFriendId(friendDto.getUserFriendId())
                .isAccepted(friendDto.getIsAccepted())
                .friendsSince(friendDto.getFriendsSince())
                .male(friendDto.getMale())
                .build();
    }

    public static FriendDto mapToFriendDto(Friends friends){
        return FriendDto.builder()
                .friendship_id(friends.getFriendship_id())
                .userId(friends.getUserId())
                .userFriendId(friends.getUserFriendId())
                .isAccepted(friends.getIsAccepted())
                .friendsSince(friends.getFriendsSince())
                .male(friends.getMale())
                .build();
    }
}
