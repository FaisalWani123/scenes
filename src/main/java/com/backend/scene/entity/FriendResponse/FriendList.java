package com.backend.scene.entity.FriendResponse;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class FriendList {
    private Integer friendshipId;
    private Integer friendUserId;
    private String friendFirstName;
    private String friendLastName;
    private Boolean requestAccepted;
}
