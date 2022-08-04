package com.alkemy.challengejava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alkemy.challengejava.entity.GenreEntity;

@Repository
public interface GenreRepository extends JpaRepository<GenreEntity, Long>{
    
}
