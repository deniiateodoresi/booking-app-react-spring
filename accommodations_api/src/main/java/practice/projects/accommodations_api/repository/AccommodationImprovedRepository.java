package practice.projects.accommodations_api.repository;

import practice.projects.accommodations_api.domain.Accommodation;
import practice.projects.accommodations_api.domain.AccommodationKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccommodationImprovedRepository extends JpaRepository<Accommodation, AccommodationKey> {
    Accommodation findByName(String name);
    @Modifying
    @Query("update Accommodation a set a.review = ?2 where a.name = ?1")
    void updateAccommodation(String name, int review, List<String> remarks);

    void deleteByName(String name);
    @Modifying
    @Query(value = "insert into demo_transactions (name, town, description) VALUES (?1, ?2, ?3)", nativeQuery = true)
    void insertIntoAccommodationTable(String name, String town, String description);
}
