package com.company.appwarehausespringdatarest.payload;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class OutputDto {
    private Timestamp date;
    private Integer warehauseId;
    private Integer clientId;
    private Integer currencyId;
    private String factureNumber;
    private String code;

}
