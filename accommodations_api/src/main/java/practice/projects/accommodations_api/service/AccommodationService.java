package practice.projects.accommodations_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.projects.accommodations_api.domain.Accommodation;
import practice.projects.accommodations_api.domain.AccommodationTown;
import practice.projects.accommodations_api.domain.AccommodationType;
import practice.projects.accommodations_api.repository.AccommodationImprovedRepository;

import java.util.ArrayList;
import java.util.List;

@Service
//@Transactional

public class AccommodationService {
    @Autowired
    AccommodationImprovedRepository accommodationImprovedRepo;

    @Bean
    ApplicationRunner initAccommodationList() {

        return args -> {
            List<String> remarks = new ArrayList<>();
            remarks.add("nice");
            remarks.add("frumos");
            accommodationImprovedRepo.save(new Accommodation("accommodationUnit-1", "description_nice_amazing_1", AccommodationTown.BIHOR, 150.0, AccommodationType.HOTEL, 5, remarks));
            accommodationImprovedRepo.save(new Accommodation("accommodationUnit-2", "description_nice_amazing_2", AccommodationTown.SUCEAVA, 170.0, AccommodationType.HOTEL, 3, new ArrayList<>()));
            accommodationImprovedRepo.save(new Accommodation("accommodationUnit-3", "description_nice_amazing_3", AccommodationTown.MURES, 160.0, AccommodationType.PENSION, 3, new ArrayList<>()));
            accommodationImprovedRepo.save(new Accommodation("accommodationUnit-4", "description_nice_amazing_4", AccommodationTown.TIMISOARA, 110.0, AccommodationType.HOTEL, 4, new ArrayList<>()));
            accommodationImprovedRepo.save(new Accommodation("accommodationUnit-5", "description_nice_amazing_5", AccommodationTown.HARGHITA, 109.0, AccommodationType.PENSION, 5, new ArrayList<>()));
        };
    }
    public List<Accommodation> getAllAccommodations() {
        return accommodationImprovedRepo.findAll();
    }

    public void saveAccommodation(Accommodation accommodation) {
        System.out.println("AM AJUNS");
        accommodationImprovedRepo.save(accommodation);
    }
//    @Transactional(noRollbackFor = { IllegalArgumentException.class })
    @Transactional
    public void saveTransactionalAccommodation(String name, String town, String description) {
        accommodationImprovedRepo.insertIntoAccommodationTable(name, town, description);
        Accommodation demoAccommodation = new Accommodation(name, description, AccommodationTown.valueOf(town), 0.0, AccommodationType.HOTEL, 0, new ArrayList<>());
        saveAccommodation(demoAccommodation);
    }

    public void deleteAccommodation(String accommodationName) {
        Accommodation foundAccommodation = accommodationImprovedRepo.findByName(accommodationName);
        accommodationImprovedRepo.delete(foundAccommodation);
    }

    public void updateAccommodation(String name, int review, List<String> remarks) {
        //accommodationImprovedRepo.updateAccommodation(name, review, remarks);
        Accommodation accommodation = accommodationImprovedRepo.findByName(name);
        accommodation.setReview(review);
        accommodation.setRemarks(remarks);
        accommodationImprovedRepo.save(accommodation);
    }
}
