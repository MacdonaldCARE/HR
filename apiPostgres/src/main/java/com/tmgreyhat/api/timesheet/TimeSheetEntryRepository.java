package com.tmgreyhat.api.timesheet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface TimeSheetEntryRepository extends JpaRepository<TimeSheetEntry, Long>     {
    @Query("select t from TimeSheetEntry t where t.employeeNumber = ?1 and t.date = ?2")
    List<TimeSheetEntry> findByEmployeeNumberAndDate(String employeeNumber, Date date);
}
