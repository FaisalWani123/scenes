package com.backend.scene.entity.SceneRequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateGuestListRequest {
    private Integer userId;
    private Integer sceneId;
    private Boolean allowed;
}
