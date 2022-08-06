package com.company.appwarehausespringdatarest.repasitory;

import com.company.appwarehausespringdatarest.entity.Output;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OutputRepository extends JpaRepository<Output,Integer> {
    @Query(value = "select count(*) from output",nativeQuery = true)
    long countAllOutput();
}
