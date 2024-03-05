package com.backend.scene.auth;

import com.backend.scene.config.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/demo")
public class DemoController {


    @GetMapping
    public ResponseEntity<String>sayHello(){
        return ResponseEntity.ok("hello from secured endpoint");
    }


}
