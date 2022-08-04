package com.alkemy.challengejava.dto;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CharacterDTO {
    private Long id;

    private String image;

    private String name;

    private int age;

    private int weight;

    private String history;

    private Set<MovieDTO> movies;

    private boolean deleted;
}
