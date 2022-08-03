package codemart.projects.accommodations_api.controller;

import codemart.projects.accommodations_api.domain.AccommodationTown;
import codemart.projects.accommodations_api.domain.AccommodationType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TownsController {

    @GetMapping("/towns")
    public AccommodationTown[] getAllTowns() {
        return AccommodationTown.values();
    }

}
