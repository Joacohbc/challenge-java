package com.alkemy.challengejava.dto;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenreDTO {
    private Long id;

    private String name;

    private String image;
    
    private Set<MovieDTO> movies;
    
    // private boolean deleted;
}
