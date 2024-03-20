package com.backend.scene.mapper;


import com.backend.scene.dto.SceneDto;
import com.backend.scene.entity.Scene;

import java.sql.Date;
import java.util.List;

public class sceneMapper {

    public static Scene mapToScene(SceneDto sceneDTO) {

        Scene scene = new Scene(
                sceneDTO.getSceneId(),
                sceneDTO.getEstablishmentId(),
                sceneDTO.getPackageId(),
                sceneDTO.getBookedBy(),
                sceneDTO.getGuestList(),
                sceneDTO.getBill(),
                sceneDTO.getPaid(),
                sceneDTO.getDate(),
                sceneDTO.getConfirmed(),
                sceneDTO.getAllGuestsAccepted()
        );
        return scene;

    }
    public static SceneDto mapToSceneDto(Scene scene) {

        SceneDto sceneDto = new SceneDto(
                scene.getSceneId(),
                scene.getEstablishmentId(),
                scene.getPackageId(),
                scene.getBookedBy(),
                scene.getGuestList(),
                scene.getBill(),
                scene.getPaid(),
                scene.getDate(),
                scene.getConfirmed(),
                scene.getAllGuestsAccepted()
        );
        return sceneDto;

    }



}


