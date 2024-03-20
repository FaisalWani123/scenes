package com.backend.scene.repository;

import com.backend.scene.entity.Establishment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstablishmentRepository extends JpaRepository<Establishment, Integer> {
    Optional<Establishment> findEstablishmentByName (String name);


}
