package com.company.appwarehausespringdatarest.repasitory;

import com.company.appwarehausespringdatarest.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;



public interface AttachmentRepository extends JpaRepository<Attachment,Integer> {
}
