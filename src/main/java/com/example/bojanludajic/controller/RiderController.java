package com.example.bojanludajic.controller;

import com.example.bojanludajic.model.Breed;
import com.example.bojanludajic.model.Favorite;
import com.example.bojanludajic.model.Horse;
import com.example.bojanludajic.model.Rider;
import com.example.bojanludajic.repository.RiderRepository;
import com.example.bojanludajic.service.RiderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/riders")
public class RiderController {

    private final RiderService riderService;

    public RiderController(RiderService riderService) {
        this.riderService = riderService;
    }

    @GetMapping("/")
    public ResponseEntity<?>  getRiders() {
        return ResponseEntity.ok(riderService.getAllRiders());
    }

    @GetMapping("/{id_rider}")
    public ResponseEntity<Rider> getRider(@PathVariable @Min(1) Integer id_rider) {
        return ResponseEntity.ok(riderService.getRiderById(id_rider));
    }

    @PostMapping("/")
    public ResponseEntity<?>  addRider(@RequestBody Rider rider) {
        Rider saved = riderService.saveRider(rider);
        if(saved == null) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PostMapping("/{id_rider}/horse")
    public ResponseEntity<?> addFavoriteHorse(@PathVariable @Min(1) int id_rider, @Valid Horse horse) {
        try {
            Favorite favorite = riderService.saveFavorite(id_rider, horse);

            return ResponseEntity.status(HttpStatus.CREATED).body(favorite);
        } catch(IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id_rider}/horses")
    public ResponseEntity<?> getFavoriteHorses(@PathVariable int id_rider) {
        try {
            List<Horse> horses = riderService.getFavorites(id_rider);

            return ResponseEntity.ok(horses);
        } catch(IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id_rider}")
    public ResponseEntity<?> deleteRider(@PathVariable @Min(1) int id_rider) {
        try {
            riderService.deleteRider(id_rider);
            return ResponseEntity.ok("Rider deleted");
        } catch(IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{first_name}/{last_name}/breeds")
    public ResponseEntity<?> getFavoriteBreeds(@PathVariable @Length(min = 2) String first_name, @PathVariable @NotEmpty String last_name) {
        Set<Breed> breeds = riderService.getFavoriteBreeds(first_name, last_name);
        return ResponseEntity.ok(breeds);
    }

}
