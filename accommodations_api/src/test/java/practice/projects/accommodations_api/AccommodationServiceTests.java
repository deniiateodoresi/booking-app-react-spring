package practice.projects.accommodations_api;

import practice.projects.accommodations_api.domain.Accommodation;
import practice.projects.accommodations_api.domain.AccommodationTown;
import practice.projects.accommodations_api.domain.AccommodationType;
import practice.projects.accommodations_api.repository.AccommodationImprovedRepository;
import practice.projects.accommodations_api.service.AccommodationService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("tester")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AccommodationServiceTests {
    @Autowired
    AccommodationImprovedRepository accommodationImprovedRepository;
    @Autowired
    AccommodationService accommodationService;

    @BeforeAll
    public void deleteAccommodations() {
        accommodationImprovedRepository.deleteAll();
    }

    @Test
    public void test1_InsertIntoDB() throws Exception {
        assertEquals(accommodationService.getAllAccommodations().size(), 0);

        Accommodation accommodation1 = new Accommodation("accommodationMockDB1", "exportedFromTest", AccommodationTown.BIHOR, 156.98, AccommodationType.HOTEL, 0, new ArrayList<>());
        Accommodation accommodation2 = new Accommodation("accommodationMockDB2", "exportedFromTest", AccommodationTown.SUCEAVA, 188.88, AccommodationType.HOTEL, 0, new ArrayList<>());
        Accommodation accommodation3 = new Accommodation("accommodationMockDB3", "exportedFromTest", AccommodationTown.MARAMURES, 119.90, AccommodationType.PENSION, 0, new ArrayList<>());

        accommodationService.saveAccommodation(accommodation1);
        accommodationService.saveAccommodation(accommodation2);
        accommodationService.saveAccommodation(accommodation3);

        assertEquals(accommodationService.getAllAccommodations().size(), 3);
    }

    @Test
    public void test2_UpdateDataDB() {
        Accommodation accommodationToUpdate = accommodationImprovedRepository.findByName("accommodationMockDB3");
        assertEquals(accommodationToUpdate.getReview(), 0);
        assertEquals(accommodationToUpdate.getRemarks().size(), 0);

        List<String> remarks = new ArrayList<>();
        remarks.add("nice nice");
        accommodationService.updateAccommodation("accommodationMockDB3", 5, remarks);
        Accommodation accommodationUpdated = accommodationImprovedRepository.findByName("accommodationMockDB3");
        assertEquals(accommodationUpdated.getReview(), 5);
        assertEquals(accommodationUpdated.getRemarks().size(), 1);
    }

    @Test
    public void test3_DeleteDataDB() {
        assertEquals(accommodationService.getAllAccommodations().size(), 3);
        accommodationService.deleteAccommodation("accommodationMockDB2");
        assertEquals(accommodationService.getAllAccommodations().size(), 2);
    }
}
