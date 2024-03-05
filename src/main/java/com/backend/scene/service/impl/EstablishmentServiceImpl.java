package com.backend.scene.service.impl;

import com.backend.scene.dto.EstablishmentDto;
import com.backend.scene.entity.Establishment;
import com.backend.scene.mapper.establishmentMapper;
import com.backend.scene.repository.EstablishmentRepository;
import com.backend.scene.service.EstablishmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstablishmentServiceImpl implements EstablishmentService {

    private EstablishmentRepository establishmentRepository;

    public EstablishmentServiceImpl(EstablishmentRepository establishmentRepository) {
        this.establishmentRepository = establishmentRepository;
    }


    @Override
    public EstablishmentDto findEstablishmentById(Integer id) {
        Establishment establishment = establishmentRepository.findById(id).orElseThrow(null);
        return establishmentMapper.mapToEstablishmentDto(establishment);
    }

    @Override
    public List<EstablishmentDto> findAll() {
        List<Establishment> allEstablishments = establishmentRepository.findAll();
        return allEstablishments.stream().map(establishmentMapper::mapToEstablishmentDto).collect(Collectors.toList());
    }

    @Override
    public EstablishmentDto findEstablishmentByName(String name) {
        Establishment establishment = establishmentRepository.findEstablishmentByName(name).orElseThrow(null);
        return establishmentMapper.mapToEstablishmentDto(establishment);

    }


}
