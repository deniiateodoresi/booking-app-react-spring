package practice.projects.accommodations_api.controller;

import practice.projects.accommodations_api.utils.JwtUtil;
import practice.projects.accommodations_api.configs.MyUserDetailsService;
import practice.projects.accommodations_api.domain.Accommodation;
import practice.projects.accommodations_api.domain.AuthenticationRequest;
import practice.projects.accommodations_api.domain.AuthenticationResponse;
import practice.projects.accommodations_api.service.AccommodationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AccommodationsController {
    @Autowired
    private AccommodationService service;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtTokenUtil;

    @RequestMapping(value ="/accommodations", method = RequestMethod.GET)
    public List<Accommodation> getAllAccommodations() {
        System.out.println(service.getAllAccommodations());
        return service.getAllAccommodations();
    }

    @RequestMapping(value ="/accommodations", method = RequestMethod.POST)
    public void saveAccommodation(@RequestBody Accommodation accommodation) {
        service.saveAccommodation(accommodation);
    }

    @RequestMapping(value ="/accommodations", method = RequestMethod.DELETE )
    public void deleteAccommodation(@RequestParam(value = "name") String accommodationName) {
        service.deleteAccommodation(accommodationName);
    }

    @RequestMapping(value ="/accommodations", method = RequestMethod.PUT)
    public void updateAccommodation(@RequestParam(value = "name") String name, @RequestParam(value = "review") int review, @RequestParam(value = "remarks") List<String> remarks) {
        service.updateAccommodation(name, review, remarks);
    }

    @RequestMapping(value ="/accommodations", method = RequestMethod.PATCH)
    public void saveAccommodationTransactional(@RequestParam(value = "name") String name, @RequestParam(value = "town") String town, @RequestParam(value = "description") String description) {
        service.saveTransactionalAccommodation(name, town, description);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}
