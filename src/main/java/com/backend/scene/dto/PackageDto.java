package com.backend.scene.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PackageDto {
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


