package com.alkemy.challengejava.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.alkemy.challengejava.dto.GenreDTO;
import com.alkemy.challengejava.entity.GenreEntity;


// No es un tipo principal, ni Service, ni Entity, ni Controller. Por eso le pongo componente
@Component 
public class GenreMapper {

    // El 2 es lo mismo que un "To"

    public GenreEntity genreDTO2Entity(GenreDTO dto) {
        GenreEntity entity = new GenreEntity();
        entity.setName(dto.getName());
        entity.setImage(dto.getImage());
        return entity;
    }

    public GenreDTO genreEntity2DTO(GenreEntity entity) {
        GenreDTO dto = new GenreDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setImage(entity.getImage());
        return dto;
    }

    public List<GenreDTO> genreEntityList2DTOList(List<GenreEntity> entities) {
        List<GenreDTO> dtos = new ArrayList<>();
        for (GenreEntity entity : entities) {
            dtos.add(genreEntity2DTO(entity));
        }
        return dtos;
    }
}
