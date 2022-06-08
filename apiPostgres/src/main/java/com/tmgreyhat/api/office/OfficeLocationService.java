package com.tmgreyhat.api.office;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfficeLocationService {

    private final OfficeLocationRepository officeLocationRepository;

    public OfficeLocationService(OfficeLocationRepository officeLocationRepository) {
        this.officeLocationRepository = officeLocationRepository;
    }

    public OfficeLocation createOfficeLocation(OfficeLocation officeLocation) {
        return officeLocationRepository.save(officeLocation);
    }

    public List<OfficeLocation> getOfficeLocations(){

        return officeLocationRepository.findAll();
    }


}
