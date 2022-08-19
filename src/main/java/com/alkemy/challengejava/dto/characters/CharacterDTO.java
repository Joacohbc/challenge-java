package com.alkemy.challengejava.dto.characters;

import java.util.Set;

import com.alkemy.challengejava.dto.movies.MovieDTO;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

// Que el Equals unicamente se base en el campo ID
// para determinar si son o no iguales
@EqualsAndHashCode(of = "id")
public class CharacterDTO {
    private Long id;
    private String image;
    private String name;
    private Integer age;
    private Integer weight;
    private String history;
    private Set<MovieDTO> movies;
}
