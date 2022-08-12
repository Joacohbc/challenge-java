package com.alkemy.challengejava.service.implementations;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.challengejava.dto.GenreDTO;
import com.alkemy.challengejava.entity.GenreEntity;
import com.alkemy.challengejava.mapper.GenreMapper;
import com.alkemy.challengejava.repository.GenreRepository;
import com.alkemy.challengejava.service.GenreService;

@Service // Le indico a SpringBoot que estos una clase Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    // Un Mapper para parsear valores
    private GenreMapper mapper;

    @Autowired
    // Un Repository para trabjar con la BD
    private GenreRepository repository;

    public GenreDTO save(GenreDTO dto) {
        // Guardo el DTO (mappeado a Entity)
        GenreEntity saved = repository.save(mapper.DTO2Entity(dto));

        // Retornorno al Entity guardada (mappeado a DTO)
        return mapper.Entity2DTO(saved);
    }

    public List<GenreDTO> getAll() {
        return mapper.EntityList2DTOList(new HashSet<>(repository.findAll()));
    }
}
