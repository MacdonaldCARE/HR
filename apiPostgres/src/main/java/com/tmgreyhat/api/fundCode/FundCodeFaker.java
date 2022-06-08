package com.tmgreyhat.api.fundCode;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FundCodeFaker {

    @Bean
    CommandLineRunner commandLineRunnerFundCodeFaker(
            FundCodeRepository fundCodeRepository) {
        return args->{

           //Salary To Be Charged To: PN: …UFFPZW2002………. FC: ………US2QS……. ACTIVITY ID.… 1.1.48 BU: ZWE02…..................
          /*  fundCodeRepository.save(new FundCode( "UFFPZW2002/US2QS/1.148/ZWE02"));
            fundCodeRepository.save(new FundCode( "UFFPZW2002/US2QS/1.148/ZWE01"));
            fundCodeRepository.save(new FundCode( "UFFPZW2002/US2QS/1.148/ZWE03"));*/

        };


    }
}
