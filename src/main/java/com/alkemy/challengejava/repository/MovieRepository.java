package com.alkemy.challengejava.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alkemy.challengejava.entity.MovieEntity;

public interface MovieRepository extends JpaRepository<MovieEntity, Long> {
    
}
