package com.demo.realestateloanAPI.demo.repository;

import com.demo.realestateloanAPI.demo.entity.ApplicantInformation;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

@Configuration
public interface ApplicantionRepository extends JpaRepository<ApplicantInformation,Long> {


}
