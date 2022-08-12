package com.alkemy.challengejava.dto.characters;

import java.util.Set;

import com.alkemy.challengejava.dto.MovieDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacterDTO {
    private Long id;
    private String image;
    private String name;
    private Integer age;
    private Integer weight;
    private String history;
    private Set<MovieDTO> movies;
}
