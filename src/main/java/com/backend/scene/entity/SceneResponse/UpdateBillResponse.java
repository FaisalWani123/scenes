package com.backend.scene.entity.SceneResponse;

import com.backend.scene.entity.Scene;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpdateBillResponse {
    private Scene scene;
    private String errorMsg;
    private Integer userId;
    private Boolean confirmed;

}
