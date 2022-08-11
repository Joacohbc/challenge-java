package com.alkemy.challengejava.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alkemy.challengejava.entity.CharacterEntity;

public interface CharacterRepository extends JpaRepository<CharacterEntity, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE CharacterEntity SET name=:name,age=:age,history=:history,image=:image,weight=:weight WHERE id_character=:id")
    void update(@Param("name") String name, @Param("age") Integer age,
            @Param("history") String history, @Param("image") String image,
            @Param("weight") Integer weight,
            @Param("id") Long id);
}
