package com.alkemy.challengejava.service;

import java.util.List;

import com.alkemy.challengejava.dto.ErrorDTO;
import com.alkemy.challengejava.dto.MovieDTO;

public interface MovieService {

    // Guardar
    MovieDTO saveMovie(MovieDTO dto);
    
    // Listar 
    List<MovieDTO> getAllMovies();
    MovieDTO getMovie(Long id) throws ErrorDTO;

    // Modificar 
    void updateMovie(Long id, MovieDTO dto) throws ErrorDTO;
    void addCharacterToMovie(Long idMovie, Long idCharacter) throws ErrorDTO;
    void removeCharacterFromMovie(Long idMovie, Long idCharacter) throws ErrorDTO;

    // Borrar
    void deleteMovie(Long id) throws ErrorDTO;

}
