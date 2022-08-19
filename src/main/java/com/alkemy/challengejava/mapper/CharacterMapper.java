package com.alkemy.challengejava.mapper;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alkemy.challengejava.dto.characters.CharacterDTO;
import com.alkemy.challengejava.dto.movies.MovieDTO;
import com.alkemy.challengejava.entity.CharacterEntity;
import com.alkemy.challengejava.entity.MovieEntity;

@Component
public class CharacterMapper {

    @Autowired
    private MovieMapper movieMapper;

    public CharacterEntity DTO2Entity(CharacterDTO dto, boolean withMovies) {
        CharacterEntity entity = new CharacterEntity();
        entity.setId(dto.getId());
        entity.setImage(dto.getImage());
        entity.setName(dto.getName());
        entity.setAge(dto.getAge());
        entity.setWeight(dto.getWeight());
        entity.setHistory(dto.getHistory());
        // entity.setDeleted(dto.isDeleted());

        // Si se pide el Personaje con sus peliculas
        if (withMovies) {
            // Agregamos las peliculas sin sus personajes para evitar un StackOverFlow
            // a causa de llamarse entre Character y Movie infinitamente
            Set<MovieEntity> movies = new HashSet<>(movieMapper.ListDTO2ListEntity(dto.getMovies(), false));
            entity.setMovies(movies);
        } else {
            // Sino le enviamos una lista vacia en vez de null para evitar NullPoninter
            entity.setMovies(new HashSet<>());
        }

        return entity;
    }

    public CharacterDTO Entity2DTO(CharacterEntity entity, boolean withMovies) {
        CharacterDTO dto = new CharacterDTO();
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setName(entity.getName());
        dto.setAge(entity.getAge());
        dto.setWeight(entity.getWeight());
        dto.setHistory(entity.getHistory());
        // dto.setDeleted(entity.isDeleted());


        // Si se pide el Personaje con sus peliculas
        if (withMovies) {
            // Agregamos las peliculas sin sus personajes para evitar un StackOverFlow
            // a causa de llamarse entre Character y Movie infinitamente
            Set<MovieDTO> movies = new HashSet<>(movieMapper.ListEntity2ListDTO(entity.getMovies(), false));
            dto.setMovies(movies);
        } else {
            // Sino le enviamos una lista vacia en vez de null para evitar NullPoninter
            dto.setMovies(new HashSet<>());
        }
        return dto;
    }

    public List<CharacterDTO> ListEntity2ListDTO(Set<CharacterEntity> set, boolean withMovies) {
        List<CharacterDTO> dtos = new LinkedList<>();

        // Si el Set viene vacio recorro para evitar NullPointer
        if (set != null) {
            for (CharacterEntity entity : set) {
                dtos.add(Entity2DTO(entity, withMovies));
            }
        }
        return dtos;
    }

    public List<CharacterEntity> ListDTO2ListEntity(Set<CharacterDTO> set, boolean withMovies) {
        List<CharacterEntity> entities = new LinkedList<>();

        // Si el Set viene vacio recorro para evitar NullPointer
        if (set != null) {
            for (CharacterDTO dto : set) {
                entities.add(DTO2Entity(dto, withMovies));
            }
        }
        return entities;
    }
}
