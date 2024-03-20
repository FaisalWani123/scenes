package com.backend.scene.controller;


import com.backend.scene.dto.PackageDto;
import com.backend.scene.entity.Package;
import com.backend.scene.entity.PackageRequest.FindAllPackagesRequest;
import com.backend.scene.entity.PackageRequest.FindPackageByIdRequest;
import com.backend.scene.entity.PackageResponse.FindAllPackagesResponse;
import com.backend.scene.entity.PackageResponse.FindPackageByIdResponse;
import com.backend.scene.mapper.packageMapper;
import com.backend.scene.service.PackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/package/controller")
public class PackageController {

    private final PackageService packageService;

    @GetMapping("/findAll/{id}")
    public ResponseEntity<FindAllPackagesResponse> findAllOfEstablishment (@PathVariable Integer id){
        FindAllPackagesResponse response = packageService.findAllPackagesForEstablishment(id);
        if (response.getFound()){
            return ResponseEntity.ok(response);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }


}
