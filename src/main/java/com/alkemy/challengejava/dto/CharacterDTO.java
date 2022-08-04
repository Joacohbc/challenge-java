package com.alkemy.challengejava.dto;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CharacterDTO extends CharacterBasicDTO {
    private Set<MovieDTO> movies;
    private boolean deleted;
}
