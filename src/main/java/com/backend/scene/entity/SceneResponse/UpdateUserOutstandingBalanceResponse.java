package com.backend.scene.entity.SceneResponse;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class UpdateUserOutstandingBalanceResponse {
    private Integer userId;
    private Integer sceneId;
    private Integer amount;
    private String errorMsg;
    private Boolean confirmed;

}
