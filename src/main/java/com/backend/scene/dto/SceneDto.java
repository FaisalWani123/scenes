package com.backend.scene.dto;

import com.backend.scene.entity.Establishment;
import com.backend.scene.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class SceneDto {
    private Integer sceneId;

    //ive changed these two to Integers
    private Integer establishmentId;
    private Integer packageId;
    private Integer bookedBy;
    private List<Integer> guestList;
    private Integer bill;
    private Boolean paid;
    private Date date;
    private Boolean confirmed;
    private Boolean allGuestsAccepted;
}
