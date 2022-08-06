package com.company.appwarehausespringdatarest.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Client{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true,nullable = false)
    private String phoneNumber;
}
