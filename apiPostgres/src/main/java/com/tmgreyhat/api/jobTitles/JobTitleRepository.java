package com.tmgreyhat.api.jobTitles;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobTitleRepository
        extends JpaRepository<JobTitle, Long> {

    Optional<JobTitle> findByJobTitle(String jobTitle);
}
