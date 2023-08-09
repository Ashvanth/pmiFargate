package com.demo.realestateloanAPI.demo.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table
@Builder
public class ApplicantInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty
    private Long applicationID;
    @JsonProperty
    @Column(unique = true)
    private Long customerSSN ;
    @JsonProperty
    private String fullName;
    @JsonProperty
    private Long loanAmount;
    @JsonProperty
    private Long salaryAmount;
    @JsonProperty
    private Long equityAmount;
    @JsonProperty
    private boolean advisorAssigned;
}
