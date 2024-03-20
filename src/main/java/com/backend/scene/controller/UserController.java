package com.backend.scene.controller;

import com.backend.scene.dto.UserDto;
import com.backend.scene.service.UserService;
import com.backend.scene.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/controller")
public class UserController {

    private final UserService service;


    @GetMapping("findById/{id}")
    public ResponseEntity<User> getById(@PathVariable Integer id){
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<User>> getById(){
        return new ResponseEntity<>(service.findAllUsers(), HttpStatus.FOUND);
    }

    @GetMapping("/findByEmail/{email}")
    public ResponseEntity<Optional<User>> findByEmail(@PathVariable String email){
        Optional<User> foundUser = service.findFromEmail(email);

        if (foundUser.isPresent()){
            return new ResponseEntity<>(service.findFromEmail(email), HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(service.findFromEmail(email), HttpStatus.OK);
        }
    }

    @GetMapping("/findIdByEmail/{email}")
    public ResponseEntity<User> findIdByEmail(@PathVariable String email){
        User foundUser = service.findIdByEmail(email);
        if (foundUser == null){
            return new ResponseEntity<>(foundUser, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(foundUser, HttpStatus.OK);
        }
    }
}
