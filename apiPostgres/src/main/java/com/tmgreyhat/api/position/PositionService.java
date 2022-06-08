package com.tmgreyhat.api.position;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {


    private final PositionRepository positionRepository;


    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public Position addPosition(Position position) {
        return positionRepository.save(position);
    }

    public List<Position> getAllPositions() {
        return positionRepository.findAll();
    }

}
