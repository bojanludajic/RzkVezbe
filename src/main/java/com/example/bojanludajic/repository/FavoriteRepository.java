package com.example.bojanludajic.repository;

import com.example.bojanludajic.model.Favorite;
import com.example.bojanludajic.model.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {

    public List<Favorite> findByRider(Rider rider);

}
