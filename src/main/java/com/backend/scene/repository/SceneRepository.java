package com.backend.scene.repository;

import com.backend.scene.entity.Scene;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SceneRepository extends JpaRepository<Scene, Integer> {
}
