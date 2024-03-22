package com.backend.scene.controller;

import com.backend.scene.dto.FriendDto;
import com.backend.scene.entity.FriendRequest.AcceptFriendRequest;
import com.backend.scene.entity.FriendRequest.FindRelationIdRequest;
import com.backend.scene.entity.FriendResponse.FindRelationIdResponse;
import com.backend.scene.entity.FriendResponse.FriendListResponse;
import com.backend.scene.entity.FriendResponse.FriendRequestResponse;
import com.backend.scene.entity.Friends;
import com.backend.scene.mapper.friendMapper;
import com.backend.scene.repository.FriendsRepository;
import com.backend.scene.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/friend/controller")
public class FriendController {

    private final FriendService friendService;
    private final FriendsRepository friendsRepository;

    @GetMapping("/findAllFriends/{id}")
    public ResponseEntity<List<FriendDto>> findAllFriends(@PathVariable Integer id){
        List<Friends> friendList = friendsRepository.findAllFriendsByUserId(id);
        List<FriendDto> friendDtos = friendList.stream().map(friendMapper::mapToFriendDto).toList();
        return ResponseEntity.ok(friendDtos);
    }

    @PostMapping("/addFriend")
    public ResponseEntity<FriendRequestResponse> addFriend(@RequestBody FriendDto friendDto){
        friendDto.setIsAccepted(false);
        FriendRequestResponse response = friendService.addFriend(friendDto);
        if (response.getConfirmed()){
            return ResponseEntity.ok(response);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/acceptFriend")
    public ResponseEntity<FriendRequestResponse> acceptFriend(@RequestBody AcceptFriendRequest request){
        FriendRequestResponse response = friendService.acceptFriend(request);
        if (response.getConfirmed()){
            return ResponseEntity.ok(response);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/findRelationId")
    public ResponseEntity<FindRelationIdResponse> findRelationId(@RequestBody FindRelationIdRequest request){
        FindRelationIdResponse response = friendService.findRelationId(request);
        if (response.getFound()){
            return ResponseEntity.ok(response);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getFriendList/{id}")
    public ResponseEntity<FriendListResponse> getFriendList(@PathVariable Integer id){
        FriendListResponse response = friendService.getFriendList(id);
        if (response.getConfirmed()){
            return ResponseEntity.ok(response);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }





    //different put method for confirming the friend request



}
