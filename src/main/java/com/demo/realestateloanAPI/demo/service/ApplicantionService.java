package com.demo.realestateloanAPI.demo.service;

import com.demo.realestateloanAPI.demo.entity.ApplicantInformation;
import com.demo.realestateloanAPI.demo.entity.UserInfo;
import com.demo.realestateloanAPI.demo.repository.ApplicantionRepository;
import com.demo.realestateloanAPI.demo.repository.UserInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ApplicantionService {


    private static ApplicantionRepository applicantionRepository;

    private static UserInfoRepository userInfoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicantionService(ApplicantionRepository applicantionRepository, UserInfoRepository userInfoRepository) {
        this.applicantionRepository = applicantionRepository;
        this.userInfoRepository = userInfoRepository;
    }

    public String submitApplication(ApplicantInformation applicantInformation)  throws DataAccessException{
        if (validateEquityAmount(applicantInformation) && ssnValidation(applicantInformation.getCustomerSSN())) {
            applicantInformation.setAdvisorAssigned(false);
            ApplicantInformation saveApplicant = applicantionRepository.save(applicantInformation);
            return saveApplicant.getApplicationID().toString();
        } else {
            return "INVALID";
        }
    }

    public List<ApplicantInformation> fetchApplications() {
        return applicantionRepository.findAll();
    }

    public Boolean validateEquityAmount(ApplicantInformation applicantInformation) {
        double percentage = applicantInformation.getLoanAmount() * 0.15;
        log.debug("Equity amount value -----" + percentage);
        Double roundedPercentage = Double.valueOf(String.format("%.2f", percentage));
        if (applicantInformation.getEquityAmount() < roundedPercentage) {
            return false;
        } else {
            return true;
        }

    }

    public Boolean ssnValidation(Long customerSSN){
        if(customerSSN!=null && customerSSN.toString().length()==11){
            return true;
        } else {return false;}
    }

    public String addUser(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfoRepository.save(userInfo);
        return "user added to system ";
    }


}
