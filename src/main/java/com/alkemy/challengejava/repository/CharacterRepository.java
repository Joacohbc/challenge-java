package com.alkemy.challengejava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alkemy.challengejava.entity.CharacterEntity;

public interface CharacterRepository extends JpaRepository<CharacterEntity, Long> {

}
