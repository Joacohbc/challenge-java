package com.alkemy.challengejava.dto;

import java.time.LocalDate;
import java.util.Set;

import com.alkemy.challengejava.entity.Rating;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class MovieDTO {
    private Long id;

    private String image;

    private String title;

    private LocalDate creationDate;

    private Rating rating;

    private Set<CharacterDTO> characters;

    private Set<GenreDTO> genres;

    private boolean deleted;
}
