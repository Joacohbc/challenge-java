package com.alkemy.challengejava.entity;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "movie")
@Getter
@Setter

@SQLDelete(sql = "UPDATE movie SET deleted=true WHERE id_movie=?")
@Where(clause = "deleted=false")

public class MovieEntity {

        public static final String IdColumName = "id_movie";

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = IdColumName)
        private Long id;

        private String title;

        private String image;

        @Column(name = "creation_date")
        @DateTimeFormat(pattern = "dd/MM/yyyy")
        private LocalDate creationDate;

        private Rating rating;

        // Marco el Ownership de la relacion en Pelicula porque me parece que tiene mas
        // logica a nivel de negocio, aunque al ser una direccion bidireccional
        // da igual
        @ManyToMany(cascade = CascadeType.MERGE)
        @JoinTable(
                        // Nombre de la tabla de la relacion entre Personaje y Pelicula
                        name = "MOVIE_CHARACTER",

                        // FK de nuestra entidad en la relacion
                        joinColumns = @JoinColumn(name = IdColumName),

                        // FK de la otra entidad (Personaje) en la relacion
                        inverseJoinColumns = @JoinColumn(name = CharacterEntity.IdColumName))
        // TODO: Ver si hay que agregar CascadeType.PERSIST para que se cree la Pelicula con Generos y Personajes
        private Set<CharacterEntity> characters;

        @ManyToMany(cascade = CascadeType.MERGE)
        @JoinTable(name = "MOVIE_GENRE", joinColumns = @JoinColumn(name = IdColumName), inverseJoinColumns = @JoinColumn(name = GenreEntity.IdColumName))
        private Set<GenreEntity> genres;

        private boolean deleted = Boolean.FALSE;
}
