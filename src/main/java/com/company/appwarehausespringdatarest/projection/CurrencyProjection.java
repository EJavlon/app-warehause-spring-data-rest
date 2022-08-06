package com.company.appwarehausespringdatarest.projection;

import com.company.appwarehausespringdatarest.entity.Currency;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = Currency.class)
public interface CurrencyProjection {
    Integer getId();

    String getName();

    Boolean getActive();
}
