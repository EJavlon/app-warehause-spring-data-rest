package com.company.appwarehausespringdatarest.projection;

import com.company.appwarehausespringdatarest.entity.Supplier;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = Supplier.class)
public interface SupplierProjection {
    Integer getId();

    String getName();

    String getPhoneNumber();

    Boolean getActive();
}
