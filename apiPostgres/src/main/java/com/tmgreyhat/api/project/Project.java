package com.tmgreyhat.api.project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "projects")
public class Project {

    @Id
    @Column(name = "projectCode", insertable = true, unique = true)
    private String projectCode;
    @Column(unique = true)
    private String projectName;
    private String projectFunder;
}
