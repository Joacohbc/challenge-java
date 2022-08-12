package com.alkemy.challengejava.dto.characters;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacterFiltersDTO {
    private String name;
    private Integer age;
    private Integer weight;
    private Set<Long> movies;
    private String order;

    public CharacterFiltersDTO(String name, Integer age, Integer weight, Set<Long> movies, String order) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.movies = movies;
        this.order = order;
    }

    public boolean isASC() {
        return order.equalsIgnoreCase("ASC");
    }
}
