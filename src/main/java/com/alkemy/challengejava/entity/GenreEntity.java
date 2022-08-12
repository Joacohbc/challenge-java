package com.alkemy.challengejava.entity;

import java.util.Set;

import javax.persistence.CascadeType;
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
@Table (name = "genre")
@Getter
@Setter

@SQLDelete (sql = "UPDATE genre SET deleted=true WHERE id_genre=?")
@Where (clause = "deleted=false")

public class GenreEntity {
    
    public static final String IdColumName = "id_genre";

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = IdColumName)
    private Long id;

    private String name;

    private String image;

    @ManyToMany(mappedBy = "genres")
    private Set<MovieEntity> movies;

    private boolean deleted = Boolean.FALSE;
}
