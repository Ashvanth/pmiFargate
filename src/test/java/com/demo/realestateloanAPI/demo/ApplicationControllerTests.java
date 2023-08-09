package com.demo.realestateloanAPI.demo;

import com.demo.realestateloanAPI.demo.controller.ApplicationController;
import com.demo.realestateloanAPI.demo.entity.ApplicantInformation;
import com.demo.realestateloanAPI.demo.service.ApplicantionService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ApplicationControllerTests {

    @Mock
    private ApplicantionService applicantionService;

    @Autowired
    private ApplicantInformation applicantInformation;

    @InjectMocks
    private ApplicationController applicationController;

    public ApplicationControllerTests() {
        this.applicantInformation = applicantInformation.builder()
                .customerSSN(1111111111L)
                .fullName("MOCK")
                .loanAmount(1000000L)
                .equityAmount(50000L)
                .salaryAmount(8000000L)
                .build();
    }

    @Test
    public void testSubmitApplicationInformationValid() {

        when(applicantionService.submitApplication(applicantInformation)).thenReturn("12345");
        ResponseEntity responseEntity = applicationController.submitApplicationInformation(applicantInformation);
        verify(applicantionService, times(1)).submitApplication(applicantInformation);
        assert responseEntity.getStatusCode() == HttpStatus.OK;
    }

    @Test
    public void testSubmitApplicationInformationInvalid() {
        when(applicantionService.submitApplication(applicantInformation)).thenReturn("INVALID");
        ResponseEntity responseEntity = applicationController.submitApplicationInformation(applicantInformation);
        verify(applicantionService, times(1)).submitApplication(applicantInformation);
        assert responseEntity.getStatusCode() == HttpStatus.BAD_REQUEST;
    }

    @Test
    public void testSubmitApplicationInformationException() {
        when(applicantionService.submitApplication(applicantInformation)).thenThrow(new DataAccessException("Mock Exception") {
        });
        ResponseEntity responseEntity = applicationController.submitApplicationInformation(applicantInformation);
        verify(applicantionService, times(1)).submitApplication(applicantInformation);
        assert responseEntity.getStatusCode() == HttpStatus.BAD_REQUEST;
    }

}
