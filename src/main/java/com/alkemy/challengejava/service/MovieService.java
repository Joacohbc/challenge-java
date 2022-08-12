package com.alkemy.challengejava.service;

import java.util.List;

import com.alkemy.challengejava.dto.MovieDTO;

public interface MovieService {
    MovieDTO saveMovie(MovieDTO dto);
    boolean updateMovie(Long id, MovieDTO dto);
    boolean deleteMovie(Long id);
    List<MovieDTO> getAllMovies();
    MovieDTO getMovie(Long id);
}
