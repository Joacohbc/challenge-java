package com.alkemy.challengejava.mapper;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alkemy.challengejava.dto.GenreDTO;
import com.alkemy.challengejava.dto.characters.CharacterDTO;
import com.alkemy.challengejava.dto.movies.MovieDTO;
import com.alkemy.challengejava.entity.CharacterEntity;
import com.alkemy.challengejava.entity.MovieEntity;
import com.alkemy.challengejava.entity.GenreEntity;

@Component
public class MovieMapper {

    @Autowired
    private CharacterMapper charcterMapper;

    @Autowired
    private GenreMapper genreMapper;

    public MovieDTO Entity2DTO(MovieEntity entity, boolean withCharacters) {
        MovieDTO dto = new MovieDTO();

        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setTitle(entity.getTitle());
        dto.setCreationDate(entity.getCreationDate());
        dto.setRating(entity.getRating());


        // Si pide la Pelicula con sus personajes
        if (withCharacters) {
            // Le cargo las peliculas sin sus personajes para evitar un StackOverFlow
            // a causa de llamarse entre Character y Movie infinitamente
            Set<CharacterDTO> characters = new HashSet<>(
                    charcterMapper.ListEntity2ListDTO(entity.getCharacters(), false));
            dto.setCharacters(characters);
        } else {
            // Sino le enviamos una lista vacia en vez de null para evitar NullPoninter
            dto.setCharacters(new HashSet<>());
        }

        Set<GenreDTO> genres = new HashSet<>(genreMapper.EntityList2DTOList(entity.getGenres()));
        dto.setGenres(genres);
        return dto;
    }

    public MovieEntity DTO2Entity(MovieDTO dto, boolean withCharacters) {
        MovieEntity entity = new MovieEntity();

        entity.setId(dto.getId());
        entity.setImage(dto.getImage());
        entity.setTitle(dto.getTitle());
        entity.setCreationDate(dto.getCreationDate());
        entity.setRating(dto.getRating());

        if (withCharacters) {
            Set<CharacterEntity> characters = new HashSet<>(
                    charcterMapper.ListDTO2ListEntity(dto.getCharacters(), false));
            entity.setCharacters(characters);
        } else {
            entity.setCharacters(new HashSet<>());

        }

        Set<GenreEntity> genres = new HashSet<>(genreMapper.ListDTO2ListEntity(dto.getGenres()));
        entity.setGenres(genres);
        return entity;
    }

    public List<MovieDTO> ListEntity2ListDTO(Set<MovieEntity> set, boolean withCharacters) {
        List<MovieDTO> dtos = new LinkedList<>();

        // Si el Set viene vacio recorro para evitar NullPointer
        if (set != null) {
            for (MovieEntity entity : set) {
                dtos.add(Entity2DTO(entity, withCharacters));
            }
        }
        return dtos;
    }

    public List<MovieEntity> ListDTO2ListEntity(Set<MovieDTO> set, boolean withCharacters) {
        List<MovieEntity> entities = new LinkedList<>();

        // Si el Set viene vacio recorro para evitar NullPointer
        if (set != null) {
            for (MovieDTO dto : set) {
                entities.add(DTO2Entity(dto, withCharacters));
            }
        }
        return entities;
    }
}
