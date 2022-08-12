package com.alkemy.challengejava.dto;

import java.time.LocalDate;
import java.util.Set;

import com.alkemy.challengejava.dto.characters.CharacterDTO;
import com.alkemy.challengejava.entity.Rating;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class MovieDTO {
    private Long id;

    private String image;

    private String title;

    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate creationDate;

    private Rating rating;

    private Set<CharacterDTO> characters;

    private Set<GenreDTO> genres;

    private boolean deleted;
}
