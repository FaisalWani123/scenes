package com.backend.scene.service;

import com.backend.scene.dto.SceneDto;
import com.backend.scene.entity.SceneRequest.UpdateGuestListRequest;
import com.backend.scene.entity.SceneRequest.UpdateBillRequest;
import com.backend.scene.entity.SceneResponse.CreateSceneResponse;
import com.backend.scene.entity.SceneResponse.UpdateGuestListResponse;
import com.backend.scene.entity.SceneResponse.UpdateBillResponse;

public interface SceneService {

    SceneDto findScene(Integer id);

    CreateSceneResponse createScene(SceneDto sceneDto);

    UpdateBillResponse addToBill(UpdateBillRequest request);

    UpdateBillResponse removeFromBill(UpdateBillRequest request);

    UpdateGuestListResponse removeGuest(UpdateGuestListRequest request);

    UpdateGuestListResponse addGuest(UpdateGuestListRequest request);
}
