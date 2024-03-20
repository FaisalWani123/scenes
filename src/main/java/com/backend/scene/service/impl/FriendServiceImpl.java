package com.backend.scene.service.impl;

import com.backend.scene.dto.FriendDto;
import com.backend.scene.entity.FriendRequest.AcceptFriendRequest;
import com.backend.scene.entity.FriendRequest.FindRelationIdRequest;
import com.backend.scene.entity.FriendResponse.FindRelationIdResponse;
import com.backend.scene.entity.FriendResponse.FriendRequestResponse;
import com.backend.scene.entity.Friends;
import com.backend.scene.repository.FriendsRepository;
import com.backend.scene.repository.UserRepository;
import com.backend.scene.service.FriendService;
import com.backend.scene.user.User;
import org.springframework.stereotype.Service;
import com.backend.scene.mapper.friendMapper;

import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {

    private final FriendsRepository friendsRepository;
    private final UserRepository userRepository;

    public FriendServiceImpl(FriendsRepository friendsRepository,
                             UserRepository userRepository) {
        this.friendsRepository = friendsRepository;
        this.userRepository = userRepository;
    }

    private FriendRequestResponse friendRequestResponseError(String errorMsg){
        return FriendRequestResponse.builder()
                .errorMsg(errorMsg)
                .confirmed(false)
                .build();
    }

    private FindRelationIdResponse findRelationIdResponseError(String errorMsg){
        return FindRelationIdResponse.builder()
                .errorMsg(errorMsg)
                .found(false)
                .build();
    }

    private Boolean relationMatchesIds(AcceptFriendRequest request){
        FindRelationIdRequest newRequst = FindRelationIdRequest.builder()
                .mainUser(request.getUserRequestSentFrom())
                .userFriend(request.getUserRequestSentTo())
                .build();
        FindRelationIdResponse response = this.findRelationId(newRequst);
        if (response.getRelationship().getFriendship_id() == request.getFriendship_id()){
            return true;
        }
        return false;
    }

    public Boolean relationshipExists(FriendDto friendDto){
        List<Friends> friendsList = friendsRepository.findAllFriendsByUserId(friendDto.getUserId());
        if (friendsList == null) {
            return false;
        }
        for (Friends friend : friendsList){
            int existingFriendUserId = friend.getUserFriendId();
            int newFriendUserId = friendDto.getUserFriendId();
            if (existingFriendUserId == newFriendUserId){
                return true;
            }
        }
        return false;
    }

    public Boolean doesUserExist(Integer friendId){
        User user = userRepository.findById(friendId).orElse(null);
        if (user == null){
            return false;
        }
        return true;
    }

    @Override
    public FriendRequestResponse addFriend(FriendDto friendDto) {
        if (this.relationshipExists(friendDto)){
            return this.friendRequestResponseError("You are already friends");
        }
        if (!this.doesUserExist(friendDto.getUserFriendId())){
            return this.friendRequestResponseError("User does not exist");
        }
        friendDto.setIsAccepted(false);
        Friends savedRelationship = friendsRepository.save(friendMapper.mapToFriend(friendDto));

        return FriendRequestResponse.builder()
                .friends(savedRelationship)
                .confirmed(true)
                .errorMsg(null)
                .build();
    }

    @Override
    public FriendRequestResponse acceptFriend(AcceptFriendRequest acceptRequest) {
        User user = userRepository.findById(acceptRequest.getUserRequestSentFrom()).orElse(null);
        if (user == null){
            return this.friendRequestResponseError("User does not exist anymore");
        }
        Friends relationship = friendsRepository.findById(acceptRequest.getFriendship_id()).orElse(null);
        if (relationship == null){
            return this.friendRequestResponseError("Unable to find the request");
        }
        if (relationship.getIsAccepted()){
            return this.friendRequestResponseError("You have already accepted this request");
        }
        relationship.setIsAccepted(true);

        if (!this.relationMatchesIds(acceptRequest)){
            return this.friendRequestResponseError("relationship id provided is wrong");
        }


        //making a new relationship for the user who accepted the request.
        FriendDto newFriendRelation = FriendDto.builder()
                .userId(acceptRequest.getUserRequestSentTo())
                .userFriendId(acceptRequest.getUserRequestSentFrom())
                .isAccepted(true)
                .build();
        Friends savedRelationship = friendsRepository.save(friendMapper.mapToFriend(newFriendRelation));

        return FriendRequestResponse.builder()
                .newRelation(savedRelationship)
                .friends(relationship)
                .confirmed(true)
                .errorMsg(null)
                .build();
    }

    @Override
    public FindRelationIdResponse findRelationId(FindRelationIdRequest request) {
        List<Friends> friendsList = friendsRepository.findAllFriendsByUserId(request.getMainUser());
        if (friendsList == null){
            return this.findRelationIdResponseError("No friends found");
        }
        for (Friends friend : friendsList){
            Integer targetId = friend.getUserFriendId();
            Integer providedId = request.getUserFriend();
            if (targetId == providedId){
                Friends foundRelation = friendsRepository.findById(friend.getFriendship_id()).orElse(null);
                if (foundRelation == null){
                    return this.findRelationIdResponseError("this relationship cannot be found");
                }
                return FindRelationIdResponse.builder()
                        .relationship(foundRelation)
                        .errorMsg(null)
                        .found(true)
                        .build();

            }
        }
        return this.findRelationIdResponseError("this friend is not in your friendlist");

    }


}
