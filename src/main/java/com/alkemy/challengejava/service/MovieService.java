package com.alkemy.challengejava.service;

import java.util.List;

import com.alkemy.challengejava.dto.ErrorDTO;
import com.alkemy.challengejava.dto.movies.MovieDTO;
import com.alkemy.challengejava.dto.movies.MovieFiltersDTO;

public interface MovieService {

    // Guardar
    MovieDTO saveMovie(MovieDTO dto) throws ErrorDTO;
    
    // Listar 
    boolean existMovie(Long id);
    List<MovieDTO> getAllMovies();
    MovieDTO getMovie(Long id) throws ErrorDTO;
    List<MovieDTO> getByFilters(MovieFiltersDTO filts);
    
    // Modificar 
    void updateMovie(Long id, MovieDTO dto) throws ErrorDTO;
    void addCharacterToMovie(Long idMovie, Long idCharacter) throws ErrorDTO;
    void removeCharacterFromMovie(Long idMovie, Long idCharacter) throws ErrorDTO;

    // Borrar
    void deleteMovie(Long id) throws ErrorDTO;

}
