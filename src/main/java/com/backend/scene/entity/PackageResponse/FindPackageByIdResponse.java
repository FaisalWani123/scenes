package com.backend.scene.entity.PackageResponse;


import com.backend.scene.dto.PackageDto;
import com.backend.scene.entity.Package;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FindPackageByIdResponse {
    private PackageDto aPackage;
    private String errorMsg;
    private Boolean found;

}
