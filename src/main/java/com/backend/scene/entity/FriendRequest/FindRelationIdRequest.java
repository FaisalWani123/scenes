package com.backend.scene.entity.FriendRequest;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class FindRelationIdRequest {
    private Integer mainUser;
    private Integer userFriend;
}
