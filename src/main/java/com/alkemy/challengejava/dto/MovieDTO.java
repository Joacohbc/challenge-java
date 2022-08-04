package com.alkemy.challengejava.dto;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class MovieDTO extends MovieBasicDTO {
    private Set<CharacterDTO>       characters;
    private Set<GenreDTO> genres;
    private boolean deleted;
}
