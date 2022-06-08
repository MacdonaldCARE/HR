package com.tmgreyhat.api.applicationToRecruit;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationToRecruitService {

    private final ApplicationToRecruitRepository applicationToRecruitRepository;

    @Autowired
    public ApplicationToRecruitService(ApplicationToRecruitRepository applicationToRecruitRepository) {
        this.applicationToRecruitRepository = applicationToRecruitRepository;
    }

    public ApplicationToRecruit createApplicationToRecruit(ApplicationToRecruit applicationToRecruit){


        return applicationToRecruitRepository.save(applicationToRecruit);

    }


    public ApplicationToRecruit getOneApplication(Integer id) {

        return  applicationToRecruitRepository.getById(id);
    }
    public List<ApplicationToRecruit> getAllApplications() {
        // remember to filter out the done applications
        return applicationToRecruitRepository.findAll();
    }

    public List<ApplicationToRecruit> getApplicationWithStatus(String status) {

        return  applicationToRecruitRepository.findAllByStatus(status.toUpperCase());
    }
}
