package com.tmgreyhat.api.project;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, String> {


    Optional<Project> findProjectByProjectCode(String projectCode);
}
