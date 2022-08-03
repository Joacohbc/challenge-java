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
@Table (name = "genero")
@Getter
@Setter

public class GeneroEntity {
    
    public static final String IdColumName = "id_genero";

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = IdColumName)
    private Long id;

    private String nombre;

    private String imagen;

    @ManyToMany(
        mappedBy = "generos", 
        cascade = CascadeType.PERSIST, 
        fetch = FetchType.EAGER
    )
    private Set<PeliculaEntity> peliculas;

    private boolean estado;
}
