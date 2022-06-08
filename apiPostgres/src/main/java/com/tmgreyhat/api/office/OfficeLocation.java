package com.tmgreyhat.api.office;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "office_locations")
@AllArgsConstructor

@Getter
@Setter
public class OfficeLocation {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String officeLocation;

    public OfficeLocation(String officeLocation) {
        this.officeLocation = officeLocation;
    }

    public OfficeLocation(){}


}
