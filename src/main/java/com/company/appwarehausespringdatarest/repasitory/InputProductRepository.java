package com.company.appwarehausespringdatarest.repasitory;

import com.company.appwarehausespringdatarest.entity.InputProduct;
import org.springframework.data.jpa.repository.JpaRepository;



public interface InputProductRepository extends JpaRepository<InputProduct,Integer> {
}
