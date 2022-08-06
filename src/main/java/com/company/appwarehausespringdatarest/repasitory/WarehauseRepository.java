package com.company.appwarehausespringdatarest.repasitory;

import com.company.appwarehausespringdatarest.entity.Warehause;
import com.company.appwarehausespringdatarest.projection.WarehauseProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "warehause",collectionResourceRel = "warehause",excerptProjection = WarehauseProjection.class)
public interface WarehauseRepository extends JpaRepository<Warehause,Integer> {
}
