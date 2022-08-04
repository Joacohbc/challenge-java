package com.alkemy.challengejava.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name = "GENRE")
@Getter
@Setter

public class GenreEntity {
    
    public static final String IdColumName = "id_genre";

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = IdColumName)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column(unique = true)
    private String image;

    @ManyToMany(
        mappedBy = "genres", 
        cascade = CascadeType.PERSIST
    )
    private Set<MovieEntity> movies;

    private boolean deleted = Boolean.FALSE;
}
