package com.example.bojanludajic.controller;

import com.example.bojanludajic.model.Horse;
import com.example.bojanludajic.service.HorseService;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/horses")
public class HorseController {

    private final HorseService horseService;

    public HorseController(HorseService horseService) {
        this.horseService = horseService;
    }

    @GetMapping("/{id_horse}")
    public ResponseEntity<?> findById(@PathVariable @Min(1) Integer id_horse) {
        Horse horse = horseService.findById(id_horse);
        if(horse == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(horse);
    }

    @GetMapping("/search")
    public ResponseEntity<?> findByBreed(@RequestParam("idBreed") int idBreed) {
        try {
            List<Horse> horses = this.horseService.findByBreed(idBreed);

            return  ResponseEntity.ok(horses);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
