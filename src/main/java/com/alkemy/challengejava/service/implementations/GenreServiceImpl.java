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

    private void throwErrorIfNotExits(Long id){
        if(!existGenre(id)){
            throw new ErrorDTO("No existe un genero con el ID: " + id, HttpStatus.NOT_FOUND);
        }
    }
    
    @Override
    public boolean existGenre(Long id) {
        return repository.existsById(id);
    }

    @Override
    public GenreDTO saveGenre(GenreDTO dto) throws ErrorDTO {

        if (dto.getId() != null) {
            throw new ErrorDTO("No puede asignar un ID a un Genero en su creacion", HttpStatus.BAD_REQUEST);
        }


        // TODO: Valida campos no nulos en al guardar
        
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
        throwErrorIfNotExits(id);
        return mapper.Entity2DTO(repository.findById(id).get());
    }
}
