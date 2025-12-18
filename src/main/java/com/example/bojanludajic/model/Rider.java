package com.example.bojanludajic.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Validated
public class Rider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 45)
    @NotEmpty
    @NotNull
    private String name;

    @Column(name = "surname", nullable = false, length = 45)
    @NotNull
    private String surname;

    @Column(name = "address", length = 45)
    private String address;

    @Column(name = "dateOfBirth")
    private LocalDate dateOfBirth;

}