package com.backend.scene.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "friends_test_tbl")
public class FriendsTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer friendship_id;

    private Integer userId;
    private Friends userFriend;
    private Boolean isAccepted;
    @UpdateTimestamp
    private Date friendsSince;
}
