package com.alkemy.challengejava.mapper;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;
import com.alkemy.challengejava.dto.GenreDTO;
import com.alkemy.challengejava.entity.GenreEntity;

// No es un tipo principal, ni Service, ni Entity, ni Controller. Por eso le pongo componente
@Component
public class GenreMapper {

    // El 2 es lo mismo que un "To"

    // DTO -> Entity
    public GenreEntity DTO2Entity(GenreDTO dto) {
        GenreEntity entity = new GenreEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setImage(dto.getImage());
        return entity;
    }

    // Entity -> DTO
    public GenreDTO Entity2DTO(GenreEntity entity) {
        GenreDTO dto = new GenreDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setImage(entity.getImage());
        dto.setDeleted(entity.isDeleted());
        return dto;
    }

    public List<GenreDTO> EntityList2DTOList(Set<GenreEntity> set) {
        List<GenreDTO> dtos = new LinkedList<>();
        for (GenreEntity entity : set) {
            dtos.add(Entity2DTO(entity));
        }
        return dtos;
    }

    public List<GenreEntity> ListDTO2ListEntity(Set<GenreDTO> set) {
        List<GenreEntity> entities = new LinkedList<>();
        for (GenreDTO dto : set) {
            entities.add(DTO2Entity(dto));
        }
        return entities;
    }
}
