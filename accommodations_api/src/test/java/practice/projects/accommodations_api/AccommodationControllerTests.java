package practice.projects.accommodations_api;

import practice.projects.accommodations_api.controller.AccommodationsController;
import practice.projects.accommodations_api.domain.Accommodation;
import practice.projects.accommodations_api.domain.AccommodationTown;
import practice.projects.accommodations_api.domain.AccommodationType;
import practice.projects.accommodations_api.repository.AccommodationImprovedRepository;
import practice.projects.accommodations_api.service.AccommodationService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccommodationsController.class)
@ActiveProfiles("tester")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AccommodationControllerTests {
	@Autowired
	private MockMvc mvc;
	@MockBean
	private AccommodationService accommodationService;
	@MockBean
    AccommodationImprovedRepository accommodationImprovedRepository;

	@BeforeAll
	public void deleteAccommodations() {
		accommodationImprovedRepository.deleteAll();
	}

	@Test
	public void test1_InsertAccommodation() throws Exception {
		String jsonString = "{\n" +
				"    \"name\": \"accommodationMockitoTestDB3\",\n" +
				"    \"description\": \"exportedFromTest\",\n" +
				"    \"price\": 119.90,\n" +
				"    \"type\": \"PENSION\",\n" +
				"    \"town\": \"MARAMURES\",\n" +
				"    \"review\": 0,\n" +
				"    \"remarks\": []\n" +
				"}";
		Accommodation accommodation = new Accommodation("accommodationMockitoTestDB3", "exportedFromTest", AccommodationTown.MARAMURES, 119.90, AccommodationType.PENSION, 0, new ArrayList<>());
		this.mvc.perform(post("/accommodations")
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonString))
				.andExpect(status().isOk());
	}

	@Test
	public void test2_GetOne() {
//		mvc.perform(get("/accommodations")
//						.contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk())
//				.andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(accommodationUnits.size())))
//				.andExpect(jsonPath("$[0].name",is("test1")));
	}

	@Test
	public void test3_GetAllAccommodations() throws Exception {
		Accommodation accommodation1 = new Accommodation("accommodationMockDB1", "exportedFromTest", AccommodationTown.BIHOR, 156.98, AccommodationType.HOTEL, 0, new ArrayList<>());
		Accommodation accommodation2 = new Accommodation("accommodationMockDB2", "exportedFromTest", AccommodationTown.SUCEAVA, 188.88, AccommodationType.HOTEL, 0, new ArrayList<>());
		Accommodation accommodation3 = new Accommodation("accommodationMockDB3", "exportedFromTest", AccommodationTown.MARAMURES, 119.90, AccommodationType.PENSION, 0, new ArrayList<>());
		List<Accommodation> accommodationList = Arrays.asList(accommodation1, accommodation2, accommodation3);
		Mockito.when(accommodationService.getAllAccommodations()).thenReturn(accommodationList);
		mvc.perform(MockMvcRequestBuilders.get("/accommodations"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

}
