package com.backend.scene.entity;


import com.backend.scene.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "address_tbl")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    //Uni Directional
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "UserId", referencedColumnName = "id")
//    private User userId;

    private Integer userId;
    private String houseNumber;
    private String addressLine;
    private String city;
    private String postCode;
    private String state;




}
