package com.backend.scene.entity.SceneRequest;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddGuestRequest {
    private Integer userId;
    private Integer sceneId;
}
