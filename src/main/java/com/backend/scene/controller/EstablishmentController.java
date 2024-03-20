package com.backend.scene.controller;

import com.backend.scene.dto.EstablishmentDto;
import com.backend.scene.service.EstablishmentService;
import com.backend.scene.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/establishment/controller")
public class EstablishmentController {

    private final EstablishmentService service;


    @GetMapping("/{id}")
    public ResponseEntity<EstablishmentDto> getById(@PathVariable Integer id){
        return new ResponseEntity<>(service.findEstablishmentById(id), HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<EstablishmentDto>> findAll(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity<EstablishmentDto> findByName(@PathVariable String name){
        return new ResponseEntity<>(service.findEstablishmentByName(name), HttpStatus.FOUND);

    }
}
