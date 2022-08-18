package com.alkemy.challengejava.service.implementations;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.alkemy.challengejava.dto.ErrorDTO;
import com.alkemy.challengejava.dto.MovieDTO;
import com.alkemy.challengejava.entity.MovieEntity;
import com.alkemy.challengejava.entity.Rating;
import com.alkemy.challengejava.mapper.MovieMapper;
import com.alkemy.challengejava.repository.movies.MovieRepository;
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

        // Valido por si pone 0 en el Rating
        if(dto.getRating() == Rating.INVALID_OPTION){
            dto.setRating(Rating.UNA_ESTRELLA);
        }

        MovieEntity entity = mapper.DTO2Entity(dto,true);
        return mapper.Entity2DTO(repository.save(entity), true);
    }

    public void updateMovie(Long id, MovieDTO dto) throws ErrorDTO {
        // TODO Auto-generated method stub
    }

    public void deleteMovie(Long id) throws ErrorDTO {
        // TODO Auto-generated method stub
    }

    public List<MovieDTO> getAllMovies() {
        Set<MovieEntity> movies = new HashSet<>(repository.findAll());
        return mapper.ListEntity2ListDTO(movies, true);
    }

    public MovieDTO getMovie(Long id) throws ErrorDTO {
        Optional<MovieEntity> movie = repository.findById(id);
        
        if(!movie.isPresent()){
            throw new ErrorDTO("The mvoie with the id " + id + " does not exist", HttpStatus.NOT_FOUND);        
        }

        return mapper.Entity2DTO(movie.get(), true);
    }

    @Override
    public void addCharacterToMovie(Long idMovie, Long idCharacter) throws ErrorDTO {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void removeCharacterFromMovie(Long idMovie, Long idCharacter) throws ErrorDTO {
        // TODO Auto-generated method stub
        
    }
    
}
