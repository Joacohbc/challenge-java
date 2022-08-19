package com.alkemy.challengejava.dto.movies;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieFiltersDTO {
    private String image;
    private String title;
    
    private LocalDate creationDateMore;

    private LocalDate creationDateLess;

    private String order;

    public MovieFiltersDTO(String image, String title, LocalDate creationDateMore, LocalDate creationDateLess,
            String order) {
        this.image = image;
        this.title = title;
        this.creationDateMore = creationDateMore;
        this.creationDateLess = creationDateLess;
        this.order = order;
    }

    public boolean isASC() {
        return order.equalsIgnoreCase("ASC");
    }
}
