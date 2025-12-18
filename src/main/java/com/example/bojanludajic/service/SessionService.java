package com.example.bojanludajic.service;

import com.example.bojanludajic.exception.TImeNotValidException;
import com.example.bojanludajic.model.Horse;
import com.example.bojanludajic.model.Rider;
import com.example.bojanludajic.model.Session;
import com.example.bojanludajic.repository.HorseRepository;
import com.example.bojanludajic.repository.RiderRepository;
import com.example.bojanludajic.repository.SessionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;
    private final RiderRepository riderRepository;
    private final HorseRepository horseRepository;

    public SessionService(
            SessionRepository sessionRepository,
            RiderRepository riderRepository,
            HorseRepository horseRepository
    ) {
        this.sessionRepository = sessionRepository;
        this.riderRepository = riderRepository;
        this.horseRepository = horseRepository;
    }

    public Session getSessionById(Long id) {
        return sessionRepository.findById(id).orElse(null);
    }

    public Session updateTIme(Long sessionId, String time) {
        Session session = sessionRepository.findById(sessionId).orElseThrow(() -> new EntityNotFoundException("Session with id " +  sessionId + " not found"));

        int timeToInt = Integer.parseInt(time);
        if(timeToInt < 0 || timeToInt >= 24) {
            throw new TImeNotValidException("Invalid time value");
        }

        session.setTime(time);

        return sessionRepository.save(session);
    }

    public List<Session> getSessionsForRiderAndHorse(int idRider, int idHorse) {
        Rider rider = riderRepository.findById(idRider).orElse(null);
        if(rider == null) {
            throw new IllegalArgumentException("Rider not found");
        }

        Horse horse = horseRepository.findById(idHorse).orElse(null);
        if(horse == null) {
            throw new IllegalArgumentException("Horse not found");
        }

        return sessionRepository.findByRiderAndHorse(rider, horse);
    }

}
