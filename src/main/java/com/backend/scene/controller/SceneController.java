package com.backend.scene.controller;


import com.backend.scene.dto.SceneDto;
import com.backend.scene.entity.SceneRequest.UpdateGuestListRequest;
import com.backend.scene.entity.SceneRequest.UpdateBillRequest;
import com.backend.scene.entity.SceneResponse.CreateSceneResponse;
import com.backend.scene.entity.SceneResponse.UpdateGuestListResponse;
import com.backend.scene.entity.SceneResponse.UpdateBillResponse;
import com.backend.scene.service.SceneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/scene/controller")
public class SceneController {

    private final SceneService sceneService;

    @GetMapping("/{id}")
    public ResponseEntity<SceneDto> getById(@PathVariable Integer id){
        return new ResponseEntity<>(sceneService.findScene(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CreateSceneResponse> create (@RequestBody SceneDto sceneDto){
        CreateSceneResponse response = sceneService.createScene(sceneDto);
        if (response.getConfirmed()){
            return ResponseEntity.ok(response);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/payBill")
    public ResponseEntity<UpdateBillResponse> payBill (@RequestBody UpdateBillRequest request){
        UpdateBillResponse response = sceneService.removeFromBill(request);
        if (response.getConfirmed()){
            return ResponseEntity.ok(response);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/chargeBill")
    public ResponseEntity<UpdateBillResponse> chargeBill (@RequestBody UpdateBillRequest request){
        UpdateBillResponse response = sceneService.addToBill(request);
        if (response.getConfirmed()){
            return ResponseEntity.ok(response);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/removeGuest")
    public ResponseEntity<UpdateGuestListResponse> removeGuest(@RequestBody UpdateGuestListRequest request){
        UpdateGuestListResponse response = sceneService.removeGuest(request);
        if (response.getConfirmed()){
            return ResponseEntity.ok(response);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/addGuest")
    public ResponseEntity<UpdateGuestListResponse> addGuest(@RequestBody UpdateGuestListRequest request){
        UpdateGuestListResponse response = sceneService.addGuest(request);
        if (response.getConfirmed()){
            return ResponseEntity.ok(response);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }






}
