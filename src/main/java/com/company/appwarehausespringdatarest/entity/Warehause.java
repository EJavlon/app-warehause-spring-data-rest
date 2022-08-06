package com.company.appwarehausespringdatarest.entity;

import com.company.appwarehausespringdatarest.entity.template.AbsEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Warehause extends AbsEntity {
}
