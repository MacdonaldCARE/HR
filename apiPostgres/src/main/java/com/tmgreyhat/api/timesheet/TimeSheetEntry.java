package com.tmgreyhat.api.timesheet;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "timesheet_entries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TimeSheetEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String employeeNumber;
    private String project;
    private String location;
    private Double hoursWorked;


}
