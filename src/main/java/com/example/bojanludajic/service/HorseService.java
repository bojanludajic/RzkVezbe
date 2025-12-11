package com.example.bojanludajic.service;


import com.example.bojanludajic.model.Breed;
import com.example.bojanludajic.model.Horse;
import com.example.bojanludajic.repository.BreedRepository;
import com.example.bojanludajic.repository.HorseRepository;
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
        return horseRepository.findById(id).orElse(null);
    }

    public List<Horse> findByBreed(int idBreed) {
        Breed breed = breedRepository.findById(idBreed).orElse(null);
        if(breed == null) {
            throw new IllegalArgumentException("Breed not found");
        }

        return horseRepository.findByBreed(breed);
    }

}
