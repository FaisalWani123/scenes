package com.backend.scene.entity.FriendResponse;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class FriendListResponse {
    private List<FriendList> friendList;
    private String errorMsg;
    private Boolean confirmed;
}
