package com.backend.scene.entity.SceneResponse;

import com.backend.scene.entity.Scene;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpdateGuestListResponse {

    private Scene scene;
    private String errorMsg;
    private Boolean confirmed;
}
