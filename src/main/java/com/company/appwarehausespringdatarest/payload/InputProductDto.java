package com.company.appwarehausespringdatarest.payload;

import lombok.Data;

import java.sql.Date;

@Data
public class InputProductDto {
    private Integer productId;
    private Integer amount;
    private Double price;
    private Date expireDate;
    private Integer inputId;
}
