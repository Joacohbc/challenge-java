package com.alkemy.challengejava.mapper;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alkemy.challengejava.dto.CharacterDTO;
import com.alkemy.challengejava.dto.GenreDTO;
import com.alkemy.challengejava.dto.MovieDTO;
import com.alkemy.challengejava.entity.CharacterEntity;
import com.alkemy.challengejava.entity.MovieEntity;
import com.alkemy.challengejava.entity.GenreEntity;

@Component
public class MovieMapper {
    
    @Autowired
    private CharacterMapper charcterMapper;

    @Autowired
    private GenreMapper genreMapper;

    public MovieDTO Entity2DTO(MovieEntity entity){
        MovieDTO dto = new MovieDTO();

        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setTitle(entity.getTitle());
        dto.setCreationDate(entity.getCreationDate());
        dto.setRating(entity.getRating());

        Set<CharacterDTO> characters = new HashSet<>(charcterMapper.ListEntity2ListDTO(entity.getCharacters())); 
        dto.setCharacters(characters);
        
        Set<GenreDTO> genres = new HashSet<>(genreMapper.EntityList2DTOList(entity.getGenres()));
        dto.setGenres(genres);
        return dto;
    }

    public MovieEntity DTO2Entity(MovieDTO dto){
        MovieEntity entity = new MovieEntity();

        entity.setId(dto.getId());
        entity.setImage(dto.getImage());
        entity.setTitle(dto.getTitle());
        entity.setCreationDate(dto.getCreationDate());
        entity.setRating(dto.getRating());

        Set<CharacterEntity> characters = new HashSet<>(charcterMapper.ListDTO2ListEntity(dto.getCharacters()));
        entity.setCharacters(characters);
        
        Set<GenreEntity> genres = new HashSet<>(genreMapper.ListDTO2ListEntity(dto.getGenres()));
        entity.setGenres(genres);
        return entity;
    }
}
