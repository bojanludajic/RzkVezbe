package com.example.bojanludajic.service;

import com.example.bojanludajic.exception.EntityAlreadyExistsException;
import com.example.bojanludajic.model.Breed;
import com.example.bojanludajic.model.Favorite;
import com.example.bojanludajic.model.Horse;
import com.example.bojanludajic.model.Rider;
import com.example.bojanludajic.repository.FavoriteRepository;
import com.example.bojanludajic.repository.RiderRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RiderService {

    private final RiderRepository riderRepository;
    private final FavoriteRepository favoriteRepository;
    private final HorseService horseService;

    @Autowired
    public RiderService(
       RiderRepository riderRepository,
       FavoriteRepository favoriteRepository,
       HorseService horseService) {
        this.riderRepository = riderRepository;
        this.favoriteRepository = favoriteRepository;
        this.horseService = horseService;
    }

    public List<Rider> getAllRiders() {
        return riderRepository.findAll();
    }

    public Rider getRiderById(Integer id) {
        return  riderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Rider with id " + id + " not found."));
    }

    @Transactional
    public Rider saveRider(Rider rider) {
        return riderRepository.save(rider);
    }

    @Transactional
    public Favorite saveFavorite(int id_rider, Horse horse) {
        Rider rider = riderRepository.findById(id_rider).orElseThrow(() -> new EntityNotFoundException("Rider with id " + id_rider + " not found"));

        List<Favorite> favorites = favoriteRepository.findByRider(rider);
        if(favorites.stream().map(it -> it.getHorse()).collect(Collectors.toSet()).contains(horse)) {
            throw new EntityAlreadyExistsException("Rider already has this horse as his favorite");
        }

        horseService.save(horse);

        Favorite favorite = new Favorite();
        favorite.setRider(rider);
        favorite.setHorse(horse);
        favoriteRepository.save(favorite);

        return favorite;
    }

    public List<Horse> getFavorites(int id_rider) {
        Rider rider = riderRepository.findById(id_rider).orElseThrow(() -> new EntityNotFoundException("Rider with id " + id_rider + " not found."));

        List<Favorite> favorites = favoriteRepository.findByRider(rider);

        return favorites.stream().map(it -> it.getHorse()).collect(Collectors.toUnmodifiableList());
    }

    public void deleteRider(int id_rider) {
        Rider rider = riderRepository.findById(id_rider).orElseThrow(() -> new EntityNotFoundException("Rider with id " + id_rider + " not found."));

        riderRepository.delete(rider);
    }

    public Set<Breed> getFavoriteBreeds(String firstName, String lastName) {
        Rider rider = riderRepository.findByNameAndSurname(firstName, lastName);

        if(rider == null) {
            throw new EntityNotFoundException("Rider not found");
        }

        List<Favorite> favorites = favoriteRepository.findByRider(rider);

        return favorites.stream().map(it -> it.getHorse().getBreed()).collect(Collectors.toSet());
    }

}
