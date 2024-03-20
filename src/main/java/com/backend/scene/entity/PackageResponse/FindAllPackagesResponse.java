package com.backend.scene.entity.PackageResponse;

import com.backend.scene.dto.PackageDto;
import com.backend.scene.entity.Package;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class FindAllPackagesResponse {

    private List<PackageDto> packageList;
    private String errorMsg;
    private Boolean found;
}
