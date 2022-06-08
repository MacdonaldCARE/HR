package com.tmgreyhat.api.fundCode;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "fund_code")
public class FundCode {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String fundCode;

    public  FundCode(String fundCode) {
        this.fundCode = fundCode;
    }
}
