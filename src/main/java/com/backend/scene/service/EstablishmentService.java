package com.backend.scene.service;

import com.backend.scene.dto.EstablishmentDto;
import com.backend.scene.entity.Establishment;

import java.util.List;

public interface EstablishmentService {
    EstablishmentDto findEstablishmentById(Integer id);

    List<EstablishmentDto> findAll();

    EstablishmentDto findEstablishmentByName(String name);


}
