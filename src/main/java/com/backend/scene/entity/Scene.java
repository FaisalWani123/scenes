package com.backend.scene.entity;

import com.backend.scene.user.User;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.Persistent;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "scene_tbl")
public class Scene {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sceneId;

    private Integer establishmentId;

    private Integer packageId;

    private Integer bookedBy;

    private List<Integer> guestList;

    private Integer bill;

    private Boolean paid;

    @CreationTimestamp
    private Date date;

    private Boolean confirmed;

    private Boolean allGuestsAccepted;

}
