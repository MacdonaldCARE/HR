package com.tmgreyhat.api.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private   ProjectRepository projectRepository;

    /*public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }*/

    public  Project createProject(Project project){
        boolean projectExists = projectRepository
                .findProjectByProjectCode(project.getProjectCode()).isPresent();

        if(projectExists){

            throw  new IllegalStateException("Project Code  "+project.getProjectCode()+" already exist");
        }
        return  projectRepository.save(project);
    }

    public List<Project> createProjectList(List<Project> projectList){

        return  projectRepository.saveAll(projectList);
    }

    public List<Project> getAllProjects(){
        return projectRepository.findAll();
    }



}
