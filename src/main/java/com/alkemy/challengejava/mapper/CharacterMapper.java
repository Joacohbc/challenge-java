package com.alkemy.challengejava.mapper;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.alkemy.challengejava.dto.CharacterDTO;
import com.alkemy.challengejava.entity.CharacterEntity;
import com.alkemy.challengejava.entity.MovieEntity;

@Component
public class CharacterMapper {

    public CharacterEntity DTO2Entity(CharacterDTO dto){
        CharacterEntity entity = new CharacterEntity();
        entity.setId(dto.getId());
        entity.setImage(dto.getImage());
        entity.setName(dto.getName());
        entity.setAge(dto.getAge());
        entity.setWeight(dto.getWeight());
        entity.setHistory(dto.getHistory());
        // entity.setDeleted(dto.isDeleted());

        // TODO: agregar el entity.setMovie()
        entity.setMovies(new HashSet<MovieEntity>());
        return entity;
    }

    public CharacterDTO Entity2DTO(CharacterEntity entity){
        CharacterDTO dto = new CharacterDTO();
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setName(entity.getName());
        dto.setAge(entity.getAge());
        dto.setWeight(entity.getWeight());
        dto.setHistory(entity.getHistory());
        // dto.setDeleted(entity.isDeleted());

        // TODO: agregar el dto.setMovie()
        entity.setMovies(new HashSet<MovieEntity>());
        return dto;
    }

    public List<CharacterDTO> ListEntity2ListDTO(List<CharacterEntity> entities) {
        List<CharacterDTO> dtos = new LinkedList<>();
        for (CharacterEntity entity : entities) {
            dtos.add(Entity2DTO(entity));
        }
        return dtos;
    }

    // public CharacterDTO Basic2DTO(CharacterBasicDTO basic) {
    //     CharacterDTO dto = new CharacterDTO();
    //     dto.setId(basic.getId());
    //     dto.setName(basic.getName());
    //     dto.setImage(basic.getImage());
    //     dto.setAge(basic.getAge());
    //     dto.setWeight(basic.getWeight());
    //     dto.setHistory(basic.getHistory());
    //     dto.setDeleted(basic.isDeleted());
    //     // Agrego esto para evitar Null en atributo movies 
    //     dto.setMovies(new HashSet<MovieDTO>());
    //     return dto;
    // }
}
