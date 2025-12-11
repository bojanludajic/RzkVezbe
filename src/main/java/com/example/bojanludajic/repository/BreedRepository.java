package com.example.bojanludajic.repository;

import com.example.bojanludajic.model.Breed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface BreedRepository extends JpaRepository<Breed, Integer> {

}
