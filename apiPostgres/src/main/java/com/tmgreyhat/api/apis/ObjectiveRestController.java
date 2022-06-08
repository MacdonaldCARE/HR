package com.tmgreyhat.api.apis;

import com.tmgreyhat.api.objective.Objective;
import com.tmgreyhat.api.objective.ObjectiveService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/care/api/objectives")
public class ObjectiveRestController {



    private final ObjectiveService objectiveService;

    public ObjectiveRestController(ObjectiveService objectiveService) {
        this.objectiveService = objectiveService;
    }

    @PostMapping
    public Objective createObjective(Objective objective){


        return  objectiveService.createObjective(objective);
    }




}
