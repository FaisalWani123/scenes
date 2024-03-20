package com.backend.scene.entity;

import com.backend.scene.entity.Establishment;
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
@Table(name = "package_tbl")
public class Package {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer packageId;
    private Integer establishmentId;
    private String type;
    private Integer maxCapacity;
    private Integer amount;
    private String description;
    private Integer maximumAvailable;
    private Boolean isAvailable;
    private Boolean isDeleted;

}
