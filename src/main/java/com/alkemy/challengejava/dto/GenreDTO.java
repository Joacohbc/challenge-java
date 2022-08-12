package com.alkemy.challengejava.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenreDTO {
    private Long id;
    private String name;
    private String image;
    private boolean deleted;
    // No tiene Set<MoviesDTO> porque en la letra no lo pide
    // y no es necesario complicarla tanto
}
