package com.alkemy.challengejava.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "charactert") // Uso un "t" luego de "character" porque me lo toma como la palabra reservada
@Getter
@Setter
@SQLDelete (sql = "UPDATE charactert SET deleted=true WHERE id_character=?")
@Where (clause = "deleted=false")

public class CharacterEntity {

    public static final String IdColumName = "id_character";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = IdColumName)
    private Long id;

    private String name;

    private String image;
    
    private Integer age;

    private Integer weight;

    private String history;

    // No uso MERGE, ya que en al letra pide especificamente que solo se actualice
    // el Personaje (y no sus Peliculas)
    // No uso REMOVE, ya que un personaje sea borrado de una pelicula no sigmifica
    // que deje de exitir esa pelicual en la BD.
    @ManyToMany(mappedBy = "characters")
    private Set<MovieEntity> movies;

    private boolean deleted = Boolean.FALSE;
}
