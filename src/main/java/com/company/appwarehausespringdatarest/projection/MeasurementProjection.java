package com.company.appwarehausespringdatarest.projection;

import com.company.appwarehausespringdatarest.entity.Measurement;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = Measurement.class)
public interface MeasurementProjection {
    Integer getId();

    String getName();

    Boolean getActive();
}
