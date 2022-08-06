package com.company.appwarehausespringdatarest.projection;

import com.company.appwarehausespringdatarest.entity.Warehause;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = Warehause.class)
public interface WarehauseProjection {
    Integer getId();

    String getName();

    Boolean getActive();
}
