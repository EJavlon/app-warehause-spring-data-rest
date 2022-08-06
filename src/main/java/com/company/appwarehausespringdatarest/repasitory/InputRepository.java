package com.company.appwarehausespringdatarest.repasitory;

import com.company.appwarehausespringdatarest.entity.Input;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface InputRepository extends JpaRepository<Input,Integer> {

    @Query(value = "select count(*) from input",nativeQuery = true)
    long countAllInput();
}
