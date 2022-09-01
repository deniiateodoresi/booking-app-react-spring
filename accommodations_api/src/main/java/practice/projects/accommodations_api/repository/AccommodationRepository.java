package practice.projects.accommodations_api.repository;

import practice.projects.accommodations_api.domain.Accommodation;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class AccommodationRepository {

    private List<Accommodation> accommodationList = new ArrayList<>();

//    @Bean
//    ApplicationRunner initAccommodationList() {
//
//        return args -> {
//            save(new Accommodation("accommodationUnit-1", "description_nice_amazing_1", AccommodationTown.BIHOR, 150.0, AccommodationType.HOTEL, 5, new ArrayList<>()));
//            save(new Accommodation("accommodationUnit-2", "description_nice_amazing_2", AccommodationTown.SUCEAVA, 170.0, AccommodationType.HOTEL, 3, new ArrayList<>()));
//            save(new Accommodation("accommodationUnit-3", "description_nice_amazing_3", AccommodationTown.MURES, 160.0, AccommodationType.PENSION, 3, new ArrayList<>()));
//            save(new Accommodation("accommodationUnit-4", "description_nice_amazing_4", AccommodationTown.TIMISOARA, 110.0, AccommodationType.HOTEL, 4, new ArrayList<>()));
//            save(new Accommodation("accommodationUnit-5", "description_nice_amazing_5", AccommodationTown.HARGHITA, 109.0, AccommodationType.PENSION, 5, new ArrayList<>()));
//        };
//    }

    public void save(Accommodation accommodation) {
        accommodationList.add(accommodation);
    }

    public void delete(String accommodationName) {
        Accommodation accommodationToDelete = null;
        for (Accommodation a : accommodationList)
            if (Objects.equals(a.getName(), accommodationName))
                accommodationToDelete = a;
        accommodationList.remove(accommodationToDelete);
    }

    public void update(String name, int review, List<String> remarks) {
        for (Accommodation a : accommodationList)
            if (Objects.equals(a.getName(), name)) {
                a.setRemarks(remarks);
                a.setReview(review);
            }
    }

    public List<Accommodation> getAccommodationList() {
        System.out.println(accommodationList);
        return accommodationList;
    }

}
