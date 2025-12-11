package com.example.bojanludajic.repository;

import com.example.bojanludajic.model.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiderRepository extends JpaRepository<Rider, Integer> {


    public Rider findByNameAndSurname(String name, String surname);

}
