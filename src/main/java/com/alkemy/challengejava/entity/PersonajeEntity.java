package com.alkemy.challengejava.entity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "personaje")
@Getter
@Setter

public class PersonajeEntity {

    public static final String IdColumName = "id_personajes";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = IdColumName)
    private Long id;

    private String imagen;

    private String nombre;

    private int edad;

    private int peso;

    private String historia;

    // No uso MERGE, ya que en al letra pide especificamente que solo se actualice
    // el Personaje (y no sus Peliculas)
    // No uso REMOVE, ya que un personaje sea borrado de una pelicula no sigmifica
    // que deje de exitir esa pelicual en la BD.
    @ManyToMany(
        mappedBy = "elenco", 
        cascade = CascadeType.PERSIST, 
        fetch = FetchType.EAGER
    )
    private Set<PeliculaEntity> peliculas;

    private boolean estado;
}
