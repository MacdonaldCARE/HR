package com.tmgreyhat.api.jobGrade;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobGradeService {


    private  final  JobGradeRepository jobGradeRepository;

    public JobGradeService(JobGradeRepository jobGradeRepository) {
        this.jobGradeRepository = jobGradeRepository;
    }

    public List<JobGrade> getAllJobGrades() {
        return jobGradeRepository.findAll();
    }

    public  JobGrade createJobGrade(JobGrade jobGrade) {
        return jobGradeRepository.save(jobGrade);
    }

    public  void  createFromList(List<JobGrade> jobGrades) {
         jobGradeRepository.saveAll(jobGrades);
    }
}
