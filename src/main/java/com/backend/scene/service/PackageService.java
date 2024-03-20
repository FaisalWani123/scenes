package com.backend.scene.service;


import com.backend.scene.entity.Package;
import com.backend.scene.entity.PackageRequest.FindAllPackagesRequest;
import com.backend.scene.entity.PackageRequest.FindPackageByIdRequest;
import com.backend.scene.entity.PackageResponse.FindAllPackagesResponse;
import com.backend.scene.entity.PackageResponse.FindPackageByIdResponse;

import java.util.List;

public interface PackageService{

    FindPackageByIdResponse findPackage(FindPackageByIdRequest request);

    FindAllPackagesResponse findAllPackagesForEstablishment(Integer id);

    List<Package> testFindAll (Integer id);

}
