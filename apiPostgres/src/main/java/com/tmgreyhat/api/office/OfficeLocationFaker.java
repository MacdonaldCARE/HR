package com.tmgreyhat.api.office;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class OfficeLocationFaker {



    @Bean
    CommandLineRunner commandLineRunnerOfficeLocation(OfficeLocationRepository officeLocationRepository) {



        return args->{

         List<OfficeLocation> officeLocationList = Arrays.asList(

                 new OfficeLocation("Harare"),
                 new OfficeLocation("Buhera"),
                 new OfficeLocation("Chivi"),
                 new OfficeLocation("Masvingo"),
                 new OfficeLocation("ZAKA")

         );

        // officeLocationRepository.saveAll(officeLocationList);

        };
    }



}
