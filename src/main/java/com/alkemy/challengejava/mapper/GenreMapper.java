package com.alkemy.challengejava.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import com.alkemy.challengejava.dto.GenreBasicDTO;
import com.alkemy.challengejava.entity.GenreEntity;


// No es un tipo principal, ni Service, ni Entity, ni Controller. Por eso le pongo componente
@Component 
public class GenreMapper {

    // El 2 es lo mismo que un "To"

    // Basic DTO -> Entity
    public GenreEntity genreBasicDTO2Entity(GenreBasicDTO dto) {
        GenreEntity entity = new GenreEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setImage(dto.getImage());
        return entity;
    }

    // Entity -> Basic DTO
    public GenreBasicDTO genreEntity2BasicDTO(GenreEntity entity) {
        GenreBasicDTO dto = new GenreBasicDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setImage(entity.getImage());
        return dto;
    }


    // Entity -> DTO
    // public GenreDTO genreEntity2DTO(GenreEntity entity) {
    //    GenreDTO dto = new GenreDTO();
    //    dto.setId(entity.getId());
    //    dto.setName(entity.getName());
    //    dto.setImage(entity.getImage());
    //    dto.setMovies(entity.getMovies());
    //   dto.setDeleted(entity.isDeleted());
    //    return dto;
    //}

    public List<GenreBasicDTO> genreEntityList2DTOList(List<GenreEntity> entities) {
        List<GenreBasicDTO> dtos = new ArrayList<>();
        for (GenreEntity entity : entities) {
            dtos.add(genreEntity2BasicDTO(entity));
        }
        return dtos;
    }
}
