package com.backend.scene.entity.SceneRequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBillRequest {
    private Integer amount;
    private Integer userId;
    private Integer sceneId;
}
