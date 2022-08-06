package com.company.appwarehausespringdatarest.repasitory;

import com.company.appwarehausespringdatarest.entity.Measurement;
import com.company.appwarehausespringdatarest.projection.MeasurementProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path = "measurement",collectionResourceRel = "measurement",excerptProjection = MeasurementProjection.class)
public interface MeasurementRepasitory extends JpaRepository<Measurement,Integer> {

    @RestResource(path = "measurement",rel = "measurement")
    Page<Measurement> findAllByName(@Param(value = "name") String name, Pageable pageable);
}
