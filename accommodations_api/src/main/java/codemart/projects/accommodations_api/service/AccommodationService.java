package codemart.projects.accommodations_api.service;

import codemart.projects.accommodations_api.domain.Accommodation;
import codemart.projects.accommodations_api.repository.AccommodationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccommodationService {
    private final AccommodationRepository accommodationRepo;

    public AccommodationService(AccommodationRepository accommodationRepo) {
        this.accommodationRepo = accommodationRepo;
    }

    public List<Accommodation> getAllAccommodations() {
        return accommodationRepo.getAccommodationList();
    }

    public void saveAccommodation(Accommodation accommodation) {
        accommodationRepo.save(accommodation);
    }

    public void deleteAccommodation(String accommodationName) {
        accommodationRepo.delete(accommodationName);
    }

    public void updateAccommodation(String name, int review, List<String> remarks) {
        accommodationRepo.update(name, review, remarks);
    }
}
