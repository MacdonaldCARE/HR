package com.tmgreyhat.api.applicationToRecruit;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="applications_to_recruit")
@NoArgsConstructor
@Getter
@Setter
public class ApplicationToRecruit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String requestingDepartment;
    private String position;
    private String fundCode;
    private Integer quantity;
    private String gradeLevel;
    private String vacancyType;
    private String jobGrade;
    private Double salary;
    private String reportingOffice;
    private String station;
    private String areaOfOperation;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private String reasonForRecruitment;
    private String requiredQualifications;
    private String optionalQualifications;
   // @Column(columnDefinition = "default true")
    private Boolean internalRecruitment;
    private String publications;
   // @Column(columnDefinition = "default = 0.0")
    private Double adCharge;
    @CreationTimestamp
    private LocalDate createdDate;
    private String createdBy;

    private String status;


    public ApplicationToRecruit(String requestingDepartment,
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
    }public ApplicationToRecruit(String requestingDepartment,
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
                                String createdBy, String status) {
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
        this.status = status;
    }




}
