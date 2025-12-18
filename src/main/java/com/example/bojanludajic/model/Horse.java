package com.example.bojanludajic.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Horse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "fullName", nullable = false, length = 45)
    @NonNull
    private String fullName;

    @Column(name = "nickname", length = 45)
    private String nickname;

    @Column(name = "gender", nullable = false, length = 1)
    @NonNull
    private String gender;

    @Column(name = "dateOfBirth", nullable = false)
    @NonNull
    private LocalDate dateOfBirth;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "breed", nullable = false)
    @NonNull
    private Breed breed;

}