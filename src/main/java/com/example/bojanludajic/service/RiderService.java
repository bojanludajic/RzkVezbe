package com.example.bojanludajic.service;

import com.example.bojanludajic.model.Breed;
import com.example.bojanludajic.model.Favorite;
import com.example.bojanludajic.model.Horse;
import com.example.bojanludajic.model.Rider;
import com.example.bojanludajic.repository.FavoriteRepository;
import com.example.bojanludajic.repository.RiderRepository;
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

    @Autowired
    public RiderService(
       RiderRepository riderRepository,
       FavoriteRepository favoriteRepository
    ) {
        this.riderRepository = riderRepository;
        this.favoriteRepository = favoriteRepository;
    }

    public List<Rider> getAllRiders() {
        return riderRepository.findAll();
    }

    @Transactional
    public Rider saveRider(Rider rider) {
        return riderRepository.save(rider);
    }

    @Transactional
    public Favorite saveFavorite(int id_rider, Horse horse) {
        Rider rider = riderRepository.findById(id_rider).orElse(null);
        if(rider == null) {
            throw new IllegalArgumentException("Rider not found");
        }

        Favorite favorite = new Favorite();
        favorite.setRider(rider);
        favorite.setHorse(horse);
        favoriteRepository.save(favorite);

        return favorite;
    }

    public List<Horse> getFavorites(int id_rider) {
        Rider rider = riderRepository.findById(id_rider).orElse(null);
        if(rider == null) {
            throw new IllegalArgumentException("Rider not found");
        }

        List<Favorite> favorites = favoriteRepository.findByRider(rider);

        return favorites.stream().map(it -> it.getHorse()).collect(Collectors.toUnmodifiableList());
    }

    public void deleteRider(int id_rider) {
        Rider rider = riderRepository.findById(id_rider).orElse(null);
        if(rider == null) {
            throw new IllegalArgumentException("Rider not found");
        }

        riderRepository.delete(rider);
    }

    public Set<Breed> getFavoriteBreeds(String firstName, String lastName) {
        Rider rider = riderRepository.findByNameAndSurname(firstName, lastName);

        if(rider == null) {
            throw new IllegalArgumentException("Rider not found");
        }

        List<Favorite> favorites = favoriteRepository.findByRider(rider);

        return favorites.stream().map(it -> it.getHorse().getBreed()).collect(Collectors.toSet());
    }

}
