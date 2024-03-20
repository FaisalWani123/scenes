package com.backend.scene.entity.FriendResponse;

import com.backend.scene.entity.Friends;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class FriendRequestResponse {
    private Friends friends;
    private Friends newRelation;
    private String errorMsg;
    private Boolean confirmed;

}
