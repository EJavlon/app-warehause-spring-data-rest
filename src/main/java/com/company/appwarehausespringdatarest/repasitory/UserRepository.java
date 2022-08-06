package com.company.appwarehausespringdatarest.repasitory;

import com.company.appwarehausespringdatarest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface UserRepository extends JpaRepository<User,Integer> {

    boolean existsByPhoneNumber(String phoneNumber);

    @Query(value = "select count(* from ",nativeQuery = true)
    long countAllUser();
}
