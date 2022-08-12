package com.alkemy.challengejava.service.implementations;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.challengejava.dto.MovieDTO;
import com.alkemy.challengejava.mapper.MovieMapper;
import com.alkemy.challengejava.repository.MovieRepository;
import com.alkemy.challengejava.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository repository;

    @Autowired
    private MovieMapper mapper;

    public MovieDTO saveMovie(MovieDTO dto) {

        // Por si no ingresa ningun Personaje no tire error
        if(dto.getCharacters() == null){
            dto.setCharacters(new HashSet<>());
        }

        // Por si no ingresa ningun Genero que no tire error
        if(dto.getGenres() == null) {
            dto.setGenres(new HashSet<>());
        }

        return mapper.Entity2DTO(repository.save(mapper.DTO2Entity(dto)));
    }

    public boolean updateMovie(Long id, MovieDTO dto) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean deleteMovie(Long id) {
        // TODO Auto-generated method stub
        return false;
    }

    public List<MovieDTO> getAllMovies() {
        // TODO Auto-generated method stub
        return null;
    }

    public MovieDTO getMovie(Long id) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
