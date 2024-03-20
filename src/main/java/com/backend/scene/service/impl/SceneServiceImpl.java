package com.backend.scene.service.impl;

import com.backend.scene.dto.PackageDto;
import com.backend.scene.dto.SceneDto;
import com.backend.scene.entity.Package;
import com.backend.scene.entity.PackageRequest.FindPackageByIdRequest;
import com.backend.scene.entity.PackageResponse.FindAllPackagesResponse;
import com.backend.scene.entity.PackageResponse.FindPackageByIdResponse;
import com.backend.scene.entity.Scene;
import com.backend.scene.entity.SceneRequest.UpdateGuestListRequest;
import com.backend.scene.entity.SceneRequest.UpdateBillRequest;
import com.backend.scene.entity.SceneRequest.UpdateUserOutstandingBalanceRequest;
import com.backend.scene.entity.SceneResponse.CreateSceneResponse;
import com.backend.scene.entity.SceneResponse.UpdateGuestListResponse;
import com.backend.scene.entity.SceneResponse.UpdateBillResponse;
import com.backend.scene.entity.SceneResponse.UpdateUserOutstandingBalanceResponse;
import com.backend.scene.mapper.sceneMapper;
import com.backend.scene.repository.EstablishmentRepository;
import com.backend.scene.repository.PackageRepository;
import com.backend.scene.repository.SceneRepository;
import com.backend.scene.repository.UserRepository;
import com.backend.scene.service.PackageService;
import com.backend.scene.service.SceneService;
import com.backend.scene.service.UserService;
import com.backend.scene.user.User;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SceneServiceImpl implements SceneService {

    public UpdateBillResponse UpdateBillResponseError(String errorMsg){
        return UpdateBillResponse.builder()
                .errorMsg(errorMsg)
                .confirmed(false)
                .build();
    }

    public UpdateGuestListResponse UpdateGuestListResponseError(String errorMsg){
        return UpdateGuestListResponse.builder()
                .errorMsg(errorMsg)
                .confirmed(false)
                .build();
    }

    public CreateSceneResponse CreateSceneResponseError(String errorMsg){
        return CreateSceneResponse.builder()
                .errorMsg(errorMsg)
                .confirmed(false)
                .build();
    }

    private SceneRepository sceneRepository;
    private UserRepository userRepository;
    private EstablishmentRepository establishmentRepository;
    private PackageRepository packageRepository;
    private UserService userService;
    private PackageService packageService;

    public SceneServiceImpl(SceneRepository sceneRepository, UserRepository userRepository,
                            EstablishmentRepository establishmentRepository,
                            UserService userService,
                            PackageService packageService,
                            PackageRepository packageRepository
                            ) {
        this.sceneRepository = sceneRepository;
        this.userRepository = userRepository;
        this.establishmentRepository = establishmentRepository;
        this.userService = userService;
        this.packageService = packageService;
        this.packageRepository = packageRepository;
    }


    public Boolean isPackageForEstablishment(SceneDto sceneDto){
        List<Package> packageList = packageRepository.findByEstablishmentId(sceneDto.getEstablishmentId()).orElse(null);
        if (packageList == null){
            return false;
        }
        for (Package aPackage : packageList){
            int scenePackageId = sceneDto.getPackageId().intValue();
            int packageId = aPackage.getPackageId().intValue();
            if (scenePackageId == packageId){
                return true;
            }
        }
        return false;
    }

    public Boolean exceedingCapacity (SceneDto sceneDto){
        FindPackageByIdResponse response = packageService.findPackage(this.findPackageByIdBuilder(sceneDto.getPackageId()));
        PackageDto myPackageDto = response.getAPackage();
        Integer capacity = myPackageDto.getMaxCapacity();
        if (capacity <= sceneDto.getGuestList().size()){
            return true;
        }
        return false;
    }

    @Override
    public SceneDto findScene(Integer id) {
        Scene scene = sceneRepository.findById(id).orElseThrow(null);
        return sceneMapper.mapToSceneDto(scene);
    }

    public FindPackageByIdRequest findPackageByIdBuilder (Integer id){
        return FindPackageByIdRequest.builder()
                .id(id)
                .build();
    }

    @Override
    public CreateSceneResponse createScene(SceneDto sceneDto) {
        List<Integer> guestList = sceneDto.getGuestList();
        // if a guest is not in the database
        for (Integer guestId : guestList) {
            if (userRepository.findById(guestId).isEmpty()) {
                return this.CreateSceneResponseError("A user in the guest list can't be found");
            }
        }

        // if an establishment is not in the databse
        if (establishmentRepository.findById(sceneDto.getEstablishmentId()).isEmpty()){
            return this.CreateSceneResponseError("Establishment can't be found");
        }
        User bookedBy = userRepository.findById(sceneDto.getBookedBy()).orElse(null);
        if (bookedBy == null){
            return this.CreateSceneResponseError("The Scene Admin cannot be found");
        }

        // if the package is not for the establishment
        FindPackageByIdResponse response = packageService.findPackage(this.findPackageByIdBuilder(sceneDto.getPackageId()));
        if (!response.getFound()){
            return this.CreateSceneResponseError("The package cannot be found");
        }


        // if the package is not for the establishment
        if (this.isPackageForEstablishment(sceneDto) == false){
            return this.CreateSceneResponseError("The package is not for this establishment");
        }


        if(this.exceedingCapacity(sceneDto)){
            return this.CreateSceneResponseError("Exceeding Capacity for this package");
        }

        // if the scene is already in the database

        // if the bookedBy user is not a guest
        // if both the guests and establishment is in the database.
        Scene scene = sceneMapper.mapToScene(sceneDto);
        Scene savedScene = sceneRepository.save(scene);
        return CreateSceneResponse.builder()
                .confirmed(true)
                .scene(savedScene)
                .build();
    }

    @Override
    public UpdateBillResponse addToBill(UpdateBillRequest request) {
        Scene scene = sceneRepository.findById(request.getSceneId()).orElse(null);
        //if the scene isnt found
        if (scene == null){
            return this.UpdateBillResponseError("scene not found");
        }
        List<Integer> guestList = scene.getGuestList();
        // if the amount is less than 0
        if (request.getAmount() < 0){
            return this.UpdateBillResponseError("amount must be greater than 0");
        }
        User userChargedTo = userRepository.findById(request.getUserId()).orElse(null);
        //if the user is not in the database
        if (userChargedTo == null){
            return this.UpdateBillResponseError("User does not exist, please contact our tech support");
        }
        //if the user is not in the guestList of that scene
        if (!guestList.contains(userChargedTo.getId())){
            return this.UpdateBillResponseError("User is not on the guest list");
        }

        scene.setBill(scene.getBill() + request.getAmount());
        Scene savedScene = sceneRepository.save(scene);
        UpdateUserOutstandingBalanceResponse response = userService.chargeUser(
                UpdateUserOutstandingBalanceRequest.builder()
                        .userId(userChargedTo.getId())
                        .sceneId(scene.getSceneId())
                        .amount(request.getAmount())
                        .build()
        );
        if (!response.getConfirmed()){
            return this.UpdateBillResponseError("Something went wrong when updating the User outstanding balance");
        }

        //need to implement logic to add to users outstanding balance.
        return UpdateBillResponse.builder()
                .scene(savedScene)
                .userId(userChargedTo.getId())
                .confirmed(true)
                .build();
    }

    @Override
    public UpdateBillResponse removeFromBill(UpdateBillRequest request) {
        Scene scene = sceneRepository.findById(request.getSceneId()).orElse(null);
        //if the scene isnt found
        if (scene == null){
            return this.UpdateBillResponseError("Scene not found");
        }
        List<Integer> guestList = scene.getGuestList();
        // amount has to be greater than 0
        if (request.getAmount() == 0){
            return this.UpdateBillResponseError("Amount must be greater than 0");
        }
        User userPaidBy = userRepository.findById(request.getUserId()).orElseThrow(null);
        //if the user is not in the guestList of that scene
        if (!guestList.contains(userPaidBy.getId())){
            return this.UpdateBillResponseError("Use is not on the guest list");
        }
        if (request.getAmount() > scene.getBill()){
            return this.UpdateBillResponseError("Amount is greater than the bill");
        }
        UpdateUserOutstandingBalanceResponse response = userService.userPaid(
                UpdateUserOutstandingBalanceRequest.builder()
                        .sceneId(scene.getSceneId())
                        .amount(request.getAmount())
                        .userId(userPaidBy.getId())
                        .build()
        );
        if (!response.getConfirmed()){
            return this.UpdateBillResponseError(response.getErrorMsg());
        }
        //need to implement logic to remove from users outstanding balance.
        //if the user is not in the database
        scene.setBill(scene.getBill() - request.getAmount());
        Scene savedScene = sceneRepository.save(scene);
        return UpdateBillResponse.builder()
                .scene(savedScene)
                .userId(userPaidBy.getId())
                .confirmed(true)
                .build();

    }


    @Override
    public UpdateGuestListResponse removeGuest(UpdateGuestListRequest request) {
        Scene scene = sceneRepository.findById(request.getSceneId()).orElse(null);
        //if the scene isn't found
        if (scene == null){
            return this.UpdateGuestListResponseError("Scene not found");
        }
        List<Integer> guestList = scene.getGuestList();
        //if the user is not in the guestList to begin with
        if (!guestList.contains(request.getUserId())){
            return this.UpdateGuestListResponseError("User is not on the guest List");
        }
        //if the user does not have admin permissions of that scene
        if (!request.getAllowed()){
            return this.UpdateGuestListResponseError("You don't have permission to remove a guest");
        }
        scene.getGuestList().remove(request.getUserId());
        Scene savedScene = sceneRepository.save(scene);
        return UpdateGuestListResponse.builder()
                .confirmed(true)
                .scene(savedScene)
                .build();
    }

    @Override
    public UpdateGuestListResponse addGuest(UpdateGuestListRequest request) {
        request.setAllowed(true);
        Scene scene = sceneRepository.findById(request.getSceneId()).orElse(null);
        //if the scene isn't found
        if (scene == null){
            return this.UpdateGuestListResponseError("Scene not found");
        }
        //if the user to be added does not exist in the database
        if (userRepository.findById(request.getUserId()).isEmpty()){
            return this.UpdateGuestListResponseError("User does not exist");
        }

        List<Integer> guestList = scene.getGuestList();
        //if the user is already in the guestList
        if (guestList.contains(request.getUserId())){
            return this.UpdateGuestListResponseError("User is already on the guest list");
        }


        FindPackageByIdResponse response = packageService.findPackage(this.findPackageByIdBuilder(scene.getPackageId()));
        if (!response.getFound()){
            return this.UpdateGuestListResponseError("Package not found");
        }

        //if max capacity has been filled for that scene
        if (this.exceedingCapacity(sceneMapper.mapToSceneDto(scene))){
            return this.UpdateGuestListResponseError("Max capacity has been reached");
        }


        scene.getGuestList().add(request.getUserId());
        Scene savedScene = sceneRepository.save(scene);
        return UpdateGuestListResponse.builder()
                .confirmed(true)
                .scene(savedScene)
                .build();
    }

    //need to add the package logic

}


//have to implement logic for rejecting an invitation.