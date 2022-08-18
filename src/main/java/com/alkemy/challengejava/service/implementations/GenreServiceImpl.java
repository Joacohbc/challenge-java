package com.alkemy.challengejava.service.implementations;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.alkemy.challengejava.dto.ErrorDTO;
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

    @Override
    public GenreDTO saveGenre(GenreDTO dto) {
        // Guardo el DTO (mappeado a Entity)
        GenreEntity saved = repository.save(mapper.DTO2Entity(dto));

        // Retornorno al Entity guardada (mappeado a DTO)
        return mapper.Entity2DTO(saved);
    }

    @Override
    public List<GenreDTO> getAllGenres() {
        return mapper.EntityList2DTOList(new HashSet<>(repository.findAll()));
    }

    @Override
    public GenreDTO getGenre(Long id) throws ErrorDTO {
        Optional<GenreEntity> dto = repository.findById(id);
        
        if(!dto.isPresent()){
            throw new ErrorDTO("The genre with the id " + id + " does not exist", HttpStatus.NOT_FOUND);
        }

        return mapper.Entity2DTO(dto.get());
    }
}
