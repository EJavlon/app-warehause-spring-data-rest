package com.company.appwarehausespringdatarest.entity;

import com.company.appwarehausespringdatarest.entity.template.AbsEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Category extends AbsEntity {
    @ManyToOne
    private Category parentCategory;
}
