package com.alkemy.challengejava.service.implementations;

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
    private GenreMapper mapper;

    @Autowired
    private GenreRepository repository;

    public GenreDTO save(GenreDTO dto) {
        // Guardo el DTO (mappeado a Entity)
        GenreEntity saved = repository.save(mapper.genreDTO2Entity(dto));

        // Retornorno al Entity guardada (mappeado a DTO)
        return mapper.genreEntity2DTO(saved);
    }

    public List<GenreDTO> getAll() {
        return mapper.genreEntityList2DTOList(repository.findAll());
    }
}
