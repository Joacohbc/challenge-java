package com.alkemy.challengejava.entity;

import java.time.LocalDate;
import java.util.Set;

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

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "MOVIE")
@Getter
@Setter

public class MovieEntity {

        public static final String IdColumName = "id_movie";

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = IdColumName)
        private Long id;

        @Column(unique = true)
        private String title;

        @Column(unique = true)
        private String image;
        
        @Column(name = "creation_date")
        @DateTimeFormat(pattern = "yyyy/MM/dd")
        private LocalDate creationDate;

        private byte rating;

        // Marco el Ownership de la relacion en Pelicula porque me parece que tiene mas
        // logica a nivel de negocio, aunque al ser una direccion bidireccional
        // da igual
        @ManyToMany(
                fetch = FetchType.EAGER,

                // No uso MERGE, ya que en al letra pide especificamente que solo se actualice
                // la Pelicula (y no sus persoanjes)

                // No uso REMOVE, ya que un persojae sea borrado de una pelicula no sigmifica
                // que deje de exitir en al BD, ya que puede pariticipar en otras peliculas
                cascade = {
                        // Uso PERSIST ya que es necesario que cuando agrege personajes una pelicula
                        CascadeType.PERSIST,
                })
        @JoinTable(
                // Nombre de la tabla de la relacion entre Personaje y Pelicula
                name = "MOVIE_CHARACTER",

                // FK de nuestra entidad en la relacion
                joinColumns = @JoinColumn(name = IdColumName),

                // FK de la otra entidad (Personaje) en la relacion
                inverseJoinColumns = @JoinColumn(name = CharacterEntity.IdColumName))
        private Set<CharacterEntity> characters;


        @ManyToMany(
                fetch = FetchType.EAGER,
                cascade = {
                        CascadeType.PERSIST,
                })
        @JoinTable(
                name = "MOVIE_GENRE",
                joinColumns = @JoinColumn(name = IdColumName),
                inverseJoinColumns = @JoinColumn(name = GenreEntity.IdColumName))
        private Set<GenreEntity> genres;

        private boolean deleted = Boolean.FALSE;
}
