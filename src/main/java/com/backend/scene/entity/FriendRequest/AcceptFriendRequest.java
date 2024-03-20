package com.backend.scene.entity.FriendRequest;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AcceptFriendRequest {
    private Integer UserRequestSentTo;
    private Integer UserRequestSentFrom;
    private Integer friendship_id;
    private Boolean accept;
}
