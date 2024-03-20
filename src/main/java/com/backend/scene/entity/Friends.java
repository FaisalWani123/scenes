package com.backend.scene.entity;


import com.backend.scene.user.User;
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
@Table(name = "friends_tbl")
public class Friends {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer friendship_id;

    private Integer userId;
    private Integer userFriendId;
    private Boolean isAccepted;

    @UpdateTimestamp
    private Date friendsSince;
}
