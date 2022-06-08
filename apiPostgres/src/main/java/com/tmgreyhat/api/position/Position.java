package com.tmgreyhat.api.position;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "job_positions")
public class Position {

    @Id
    @GeneratedValue(strategy =  GenerationType.SEQUENCE, generator = "position_generator")
    private Long id;
    private String positionName;

    public Position(String positionName) {
        this.positionName = positionName;
    }

}
