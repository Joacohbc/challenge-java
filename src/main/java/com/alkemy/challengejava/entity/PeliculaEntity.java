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
@Table(name = "pelicula")
@Getter
@Setter

public class PeliculaEntity {

        public static final String IdColumName = "id_pelicula";

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = IdColumName)
        private Long id;

        private String imagen;

        private String titulo;

        @Column(name = "fec_creacion")
        @DateTimeFormat(pattern = "dd/MM/yyyy")
        private LocalDate fechaCreacion;

        private byte calificacion;

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
                name = "participan",

                // FK de nuestra entidad en la relacion
                joinColumns = @JoinColumn(name = IdColumName),

                // FK de la otra entidad (Personaje) en la relacion
                inverseJoinColumns = @JoinColumn(name = PersonajeEntity.IdColumName))
        private Set<PersonajeEntity> elenco;


        @ManyToMany(
                fetch = FetchType.EAGER,
                cascade = {
                        CascadeType.PERSIST,
                })
        @JoinTable(
                name = "tiene",
                joinColumns = @JoinColumn(name = IdColumName),
                inverseJoinColumns = @JoinColumn(name = GeneroEntity.IdColumName))
        private Set<GeneroEntity> generos;

        private boolean estado;
}
