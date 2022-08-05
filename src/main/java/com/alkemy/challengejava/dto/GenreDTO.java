package com.alkemy.challengejava.dto;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenreDTO extends GenreBasicDTO {
    private Set<MovieDTO> movies;
    private boolean deleted;
}
