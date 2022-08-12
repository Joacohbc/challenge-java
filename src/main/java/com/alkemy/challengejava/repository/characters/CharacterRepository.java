package com.alkemy.challengejava.repository.characters;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alkemy.challengejava.entity.CharacterEntity;

@Repository
public interface CharacterRepository extends JpaRepository<CharacterEntity, Long>, JpaSpecificationExecutor<CharacterEntity> {
    // Metodo para realizar UPDATE, unicamente de Name, Age, History, Image, Weight (no id y no movies)
    @Modifying
    @Transactional
    @Query("UPDATE CharacterEntity SET name=:name,age=:age,history=:history,image=:image,weight=:weight WHERE id_character=:id")
    void update(@Param("name") String name, @Param("age") Integer age,
            @Param("history") String history, @Param("image") String image,
            @Param("weight") Integer weight,
            @Param("id") Long id);

    // Metodo para listar por fitlros
    List<CharacterEntity> findAll(Specification<CharacterEntity> specs);
}
