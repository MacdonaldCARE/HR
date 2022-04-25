package com.tmgreyhat.api.jobTitles;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobTitleService {

    private final JobTitleRepository jobTitleRepository;


    public JobTitleService(JobTitleRepository jobTitleRepository) {
        this.jobTitleRepository = jobTitleRepository;
    }



    public List<JobTitle> getAllJobTitles() {

        return  jobTitleRepository.findAll();
    }

    public JobTitle getOneJobTitle(Long id) {

        boolean jobTitle = jobTitleRepository.findById(id).isPresent();

        if(!jobTitle){

            throw  new IllegalStateException("Job Title with ID "+id+" does not exist");
        }

        return  jobTitleRepository.getById(id);

    }

    public JobTitle updateJobTitle(JobTitle jobTitle) {

        boolean jobTitleExists = jobTitleRepository.findById(jobTitle.getId()).isPresent();

        if(!jobTitleExists){

            throw  new IllegalStateException("Job Title with ID "+jobTitle.getId()+" does not exist");
        }

        return  jobTitleRepository.save(jobTitle);
    }

    public JobTitle addJobTitle(JobTitle jobTitle) {

        boolean employeeEmailTaken = jobTitleRepository
                .findByJobTitle(jobTitle.getJobTitle()).isPresent();

        if(employeeEmailTaken){

            throw  new IllegalStateException("Job Title  with name "+jobTitle.getJobTitle()+" Already exist");
        }

        return  jobTitleRepository.save(jobTitle);

    }

    public void deleteOneJobTitle(Long id) {

        boolean jobTitleExists = jobTitleRepository.findById(id).isPresent();

        if(!jobTitleExists){

            throw  new IllegalStateException("Job title  with ID "+id+" does not exist");
        }
        jobTitleRepository.deleteById(id);
    }



}
