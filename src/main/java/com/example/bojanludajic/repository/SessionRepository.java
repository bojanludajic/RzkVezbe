package com.example.bojanludajic.repository;

import com.example.bojanludajic.model.Horse;
import com.example.bojanludajic.model.Rider;
import com.example.bojanludajic.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

    List<Session> findByRiderAndHorse(Rider rider, Horse horse);

}
