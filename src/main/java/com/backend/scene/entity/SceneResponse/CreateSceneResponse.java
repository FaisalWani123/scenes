package com.backend.scene.entity.SceneResponse;

import com.backend.scene.entity.Scene;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CreateSceneResponse {

    private Scene scene;
    private String errorMsg;
    private Boolean confirmed;


}
