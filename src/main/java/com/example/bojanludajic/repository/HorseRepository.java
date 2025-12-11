package com.example.bojanludajic.repository;

import com.example.bojanludajic.model.Breed;
import com.example.bojanludajic.model.Horse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HorseRepository extends JpaRepository<Horse, Integer> {


    public List<Horse> findByBreed(Breed breed);

}
