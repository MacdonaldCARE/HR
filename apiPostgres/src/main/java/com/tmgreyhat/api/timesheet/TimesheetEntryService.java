package com.tmgreyhat.api.timesheet;

import org.springframework.stereotype.Service;

@Service
public class TimesheetEntryService {


    private final  TimeSheetEntryRepository timeSheetEntryRepository;

    public TimesheetEntryService(TimeSheetEntryRepository timeSheetEntryRepository) {
        this.timeSheetEntryRepository = timeSheetEntryRepository;
    }

    public TimeSheetEntry createTimeSheetEntry(TimeSheetEntry timeSheetEntry) {


        Boolean timeSheetEntryDoesNotExist =
                timeSheetEntryRepository.findByEmployeeNumberAndDate(timeSheetEntry.getEmployeeNumber(), timeSheetEntry.getDate()).isEmpty();

        if (timeSheetEntryDoesNotExist) {

            return timeSheetEntryRepository.save(timeSheetEntry);
        }
      else  throw  new IllegalStateException("Timesheet entry already exists for employee number " + timeSheetEntry.getEmployeeNumber() + " and date " + timeSheetEntry.getDate());
    }


}
