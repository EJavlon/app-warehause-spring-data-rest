package com.company.appwarehausespringdatarest.projection;

import com.company.appwarehausespringdatarest.entity.Client;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = Client.class)
public interface ClientProjection {
    Integer getId();

    String getName();

    String getPhoneNumber();

    Boolean getActive();
}
