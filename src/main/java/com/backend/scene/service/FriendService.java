package com.backend.scene.service;

import com.backend.scene.dto.FriendDto;
import com.backend.scene.entity.FriendRequest.AcceptFriendRequest;
import com.backend.scene.entity.FriendRequest.FindRelationIdRequest;
import com.backend.scene.entity.FriendResponse.FindRelationIdResponse;
import com.backend.scene.entity.FriendResponse.FriendList;
import com.backend.scene.entity.FriendResponse.FriendListResponse;
import com.backend.scene.entity.FriendResponse.FriendRequestResponse;

public interface FriendService {

    FriendRequestResponse addFriend(FriendDto friendDto);
    FriendRequestResponse acceptFriend(AcceptFriendRequest acceptRequest);

    FindRelationIdResponse findRelationId(FindRelationIdRequest request);

    FriendListResponse getFriendList(Integer userId);

    FriendListResponse getPendingRequestsForUser(Integer userId);





}
