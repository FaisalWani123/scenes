package com.backend.scene.mapper;

import com.backend.scene.dto.EstablishmentDto;
import com.backend.scene.entity.Establishment;

public class establishmentMapper {

    public static Establishment mapToEstablishment(EstablishmentDto establishmentDto){
        Establishment establishment = new Establishment(
                establishmentDto.getId(),
                establishmentDto.getName(),
                establishmentDto.getRating(),
                establishmentDto.getArea(),
                establishmentDto.getAvgCostForTwo(),
                establishmentDto.getCuisine(),
                establishmentDto.getImageUrl()
        );
        return establishment;
    }

    public static EstablishmentDto mapToEstablishmentDto(Establishment establishment){
        EstablishmentDto establishmentDto = new EstablishmentDto(
                establishment.getId(),
                establishment.getName(),
                establishment.getRating(),
                establishment.getArea(),
                establishment.getAvgCostForTwo(),
                establishment.getCuisine(),
                establishment.getImageUrl()
        );
        return establishmentDto;
    }

}

