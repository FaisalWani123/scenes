package com.backend.scene.entity.SceneRequest;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpdateUserOutstandingBalanceRequest {
    private Integer sceneId;
    private Integer userId;
    private Integer amount;
}
