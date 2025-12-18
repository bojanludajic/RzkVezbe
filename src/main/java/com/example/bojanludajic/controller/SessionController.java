package com.example.bojanludajic.controller;

import com.example.bojanludajic.model.Session;
import com.example.bojanludajic.service.SessionService;
import jakarta.validation.constraints.Min;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/sessions")
public class SessionController {

    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

        @PutMapping("/{id_session}/time/{time}")
    public ResponseEntity<?> updateTime(@PathVariable @Min(1) Long id_session, @PathVariable @Validated @Length(min=2, max=2) String time){
        try {
            Session session = sessionService.updateTIme(id_session, time);
            return  ResponseEntity.ok(session);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping()
    public ResponseEntity<?> getSessionsForUserAndHorse(@RequestParam("idRider") int idRider, @RequestParam("idHorse") int idHorse) {
        try {
            List<Session> sessions = sessionService.getSessionsForRiderAndHorse(idRider, idHorse);

            return  ResponseEntity.ok(sessions);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }


}
