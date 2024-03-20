package com.backend.scene.repository;

import com.backend.scene.entity.Package;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PackageRepository extends JpaRepository<Package, Integer> {

    Optional<List<Package>> findByEstablishmentId (Integer id);
}
