package com.company.appwarehausespringdatarest.payload;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class InputDto {
    private Timestamp date;
    private Integer warehauseId;
    private Integer supplierId;
    private Integer currencyId;
    private String factureNumber;
    private String code;

}
