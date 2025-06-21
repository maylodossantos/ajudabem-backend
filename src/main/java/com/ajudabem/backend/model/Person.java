package com.ajudabem.backend.model;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Person {

    protected String name;

    protected Integer age;
    protected LocalDate birthDate;

    private String cpf;
    private String rg;

}
