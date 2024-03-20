package com.backend.scene.dto;

import com.backend.scene.user.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class UserDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
    private LocalDate DOB;
    private Integer outstandingAmount;

}
