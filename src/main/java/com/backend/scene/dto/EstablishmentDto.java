package com.backend.scene.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EstablishmentDto {
    private Integer id;
    private String name;
    private Double rating;
    private String area;
    private Double avgCostForTwo;
    private String cuisine;
    private String imageUrl;

}
