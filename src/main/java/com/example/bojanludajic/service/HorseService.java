package com.example.bojanludajic.service;


import com.example.bojanludajic.model.Breed;
import com.example.bojanludajic.model.Horse;
import com.example.bojanludajic.repository.BreedRepository;
import com.example.bojanludajic.repository.HorseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HorseService {

    private final HorseRepository horseRepository;
    private final BreedRepository breedRepository;


    public HorseService(HorseRepository horseRepository, BreedRepository breedRepository) {
        this.horseRepository = horseRepository;
        this.breedRepository = breedRepository;
    }

    public Horse findById(Integer id) {
        Horse horse = horseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Horse with id " + id + " not found" ));

        return horse;
    }

    public List<Horse> findByBreed(int idBreed) {
        Breed breed = breedRepository.findById(idBreed).orElse(null);
        if(breed == null) {
            throw new IllegalArgumentException("Breed not found");
        }

        return horseRepository.findByBreed(breed);
    }

    public void save(Horse horse) {
        horseRepository.save(horse);
    }

}
