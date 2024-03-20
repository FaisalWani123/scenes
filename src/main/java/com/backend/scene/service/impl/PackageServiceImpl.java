package com.backend.scene.service.impl;


import com.backend.scene.dto.PackageDto;
import com.backend.scene.entity.Package;
import com.backend.scene.entity.PackageRequest.FindAllPackagesRequest;
import com.backend.scene.entity.PackageRequest.FindPackageByIdRequest;
import com.backend.scene.entity.PackageResponse.FindAllPackagesResponse;
import com.backend.scene.entity.PackageResponse.FindPackageByIdResponse;
import com.backend.scene.mapper.packageMapper;
import com.backend.scene.repository.PackageRepository;
import com.backend.scene.service.PackageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PackageServiceImpl implements PackageService {

    private PackageRepository packageRepository;

    public PackageServiceImpl(PackageRepository packageRepository) {
        this.packageRepository = packageRepository;
    }

    private FindPackageByIdResponse FindPackageByIdResponseError(String errormsg){
        return FindPackageByIdResponse.builder()
                .errorMsg(errormsg)
                .found(false)
                .build();
    }

    @Override
    public FindPackageByIdResponse findPackage(FindPackageByIdRequest request) {
        Package aPackage = packageRepository.findById(request.getId()).orElse(null);
        if (aPackage == null) {
            return this.FindPackageByIdResponseError("Package not found");
        }
        return FindPackageByIdResponse.builder()
                .found(true)
                .aPackage(packageMapper.mapToPackageDto(aPackage))
                .build();

    }

    @Override
    public FindAllPackagesResponse findAllPackagesForEstablishment(Integer id) {
        List<Package> packageList = packageRepository.findByEstablishmentId(id).orElse(null);
        if (packageList == null){
            return FindAllPackagesResponse.builder()
                    .found(false)
                    .errorMsg("could not find any packages for this establishment")
                    .build();
        }
        List<PackageDto> packageDtos =  packageList.stream().map(packageMapper :: mapToPackageDto).collect(Collectors.toList());

        return FindAllPackagesResponse.builder()
                .found(true)
                .packageList(packageDtos)
                .build();
    }

    @Override
    public List<Package> testFindAll(Integer id) {
        return packageRepository.findByEstablishmentId(id).orElse(null);
    }
}
