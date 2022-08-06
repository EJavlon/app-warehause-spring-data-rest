package com.company.appwarehausespringdatarest.repasitory;

import com.company.appwarehausespringdatarest.entity.Client;
import com.company.appwarehausespringdatarest.projection.ClientProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "client",collectionResourceRel = "client",excerptProjection = ClientProjection.class)
public interface ClientRepository extends JpaRepository<Client,Integer> {
}
