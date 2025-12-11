package com.example.bojanludajic.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "rider", nullable = false)
    private Rider rider;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "horse", nullable = false)
    private Horse horse;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "time", nullable = false, length = 2)
    private String time;

    @Column(name = "type", nullable = false, length = 45)
    private String type;

    @Column(name = "description", length = 200)
    private String description;

}