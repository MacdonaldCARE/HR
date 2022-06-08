package com.tmgreyhat.api.applicationToRecruit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApplicationToRecruitRepository  extends JpaRepository<ApplicationToRecruit, Integer> {
    @Query("select a from ApplicationToRecruit a where upper(a.status) = upper(?1)")
    List<ApplicationToRecruit> findAllByStatus(String status);

}
