package com.backend.scene.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;

@Data
@Builder
public class FriendDto {
    private Integer friendship_id;
    private Integer userId;
    private Integer userFriendId;
    private Boolean isAccepted;
    private Date friendsSince;
}

