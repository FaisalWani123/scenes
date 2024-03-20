package com.backend.scene.entity.FriendResponse;

import com.backend.scene.entity.Friends;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class FindRelationIdResponse {
    private Friends relationship;
    private String errorMsg;
    private Boolean found;
}
