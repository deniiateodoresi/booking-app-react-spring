package codemart.projects.accommodations_api.controller;

import codemart.projects.accommodations_api.domain.Accommodation;
import codemart.projects.accommodations_api.service.AccommodationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AccommodationsController {
    private final AccommodationService service;

    public AccommodationsController(AccommodationService service) {
        this.service = service;
    }

    @GetMapping("/accommodations")
    public List<Accommodation> getAllAccommodations() {
        return service.getAllAccommodations();
    }

    @PostMapping("/accommodations")
    public void saveAccommodation(@RequestBody Accommodation accommodation) {
        service.saveAccommodation(accommodation);
    }

    @DeleteMapping("/accommodations")
    public void deleteAccommodation(@RequestParam(value = "name") String accommodationName) {
        service.deleteAccommodation(accommodationName);
    }

    @PutMapping("/accommodations")
    public void updateAccommodation(@RequestParam(value = "name") String name, @RequestParam(value = "review") int review, @RequestParam(value = "remarks") List<String> remarks) {
        service.updateAccommodation(name, review, remarks);
    }


}
