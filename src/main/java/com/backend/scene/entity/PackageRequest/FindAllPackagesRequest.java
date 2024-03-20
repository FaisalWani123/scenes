package com.backend.scene.entity.PackageRequest;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FindAllPackagesRequest {
    private Integer establishmentId;

}
