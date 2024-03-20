package com.backend.scene.mapper;

import com.backend.scene.dto.PackageDto;
import com.backend.scene.entity.Package;

public class packageMapper {

    public static Package mapToPackage(PackageDto packageDto){
        Package myPackage = new Package(
                packageDto.getPackageId(),
                packageDto.getEstablishmentId(),
                packageDto.getType(),
                packageDto.getMaxCapacity(),
                packageDto.getAmount(),
                packageDto.getDescription(),
                packageDto.getMaximumAvailable(),
                packageDto.getIsAvailable(),
                packageDto.getIsDeleted()
        );

        return myPackage;

    }

    public static PackageDto mapToPackageDto(Package package1){
        PackageDto packageDto = new PackageDto(
                package1.getPackageId(),
                package1.getEstablishmentId(),
                package1.getType(),
                package1.getMaxCapacity(),
                package1.getAmount(),
                package1.getDescription(),
                package1.getMaximumAvailable(),
                package1.getIsAvailable(),
                package1.getIsDeleted()
        );

        return packageDto;
    }
}
