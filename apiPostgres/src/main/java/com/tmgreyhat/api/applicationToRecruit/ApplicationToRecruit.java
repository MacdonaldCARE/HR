package com.tmgreyhat.api.applicationToRecruit;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="applications_to_recruit")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AppRecruit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String requestingDepartment;
    private String position;
    private String gradeLevel;
    private Double salary;
    private String reportingOffice;
    private String station;
    private String areaOfOperation;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reasonForRecruitment;
    private String requiredQualifications;
    private String optionalQualifications;
    private Boolean internalRecruitment;
    private String publications;
    private Double adCharge;
    private LocalDate createdDate;
    private String createdBy;

    public AppRecruit(String requestingDepartment,
                      String position,
                      String gradeLevel,
                      Double salary,
                      String reportingOffice,
                      String station,
                      String areaOfOperation,
                      LocalDate startDate,
                      LocalDate endDate,
                      String reasonForRecruitment,
                      String requiredQualifications,
                      String optionalQualifications,
                      Boolean internalRecruitment,
                      String publications,
                      Double adCharge,
                      LocalDate createdDate,
                      String createdBy) {
        this.requestingDepartment = requestingDepartment;
        this.position = position;
        this.gradeLevel = gradeLevel;
        this.salary = salary;
        this.reportingOffice = reportingOffice;
        this.station = station;
        this.areaOfOperation = areaOfOperation;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reasonForRecruitment = reasonForRecruitment;
        this.requiredQualifications = requiredQualifications;
        this.optionalQualifications = optionalQualifications;
        this.internalRecruitment = internalRecruitment;
        this.publications = publications;
        this.adCharge = adCharge;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
    }
}
