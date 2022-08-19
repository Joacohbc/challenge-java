package com.alkemy.challengejava.repository.movies;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alkemy.challengejava.entity.MovieEntity;
import com.alkemy.challengejava.entity.Rating;

public interface MovieRepository extends JpaRepository<MovieEntity, Long>, JpaSpecificationExecutor<MovieEntity> {

        // Metodo para realizar UPDATE, unicamente de Title
        @Modifying
        @Transactional
        // En el nombre de la tabla va el nombre de la Clase y en va sin punto y coma al
        // final
        @Query("UPDATE MovieEntity SET title=:title, image=:image, rating=:rating WHERE id_movie=:id")
        void update(@Param("title") String title, @Param("image") String imaage, @Param("rating") Rating rating,
                        @Param("id") Long id);

        // Metodo para listar por fitlros
        List<MovieEntity> findAll(Specification<MovieEntity> specs);
}
