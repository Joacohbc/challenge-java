package com.alkemy.challengejava.service;

import java.util.List;

import com.alkemy.challengejava.dto.ErrorDTO;
import com.alkemy.challengejava.dto.GenreDTO;

public interface GenreService {
    boolean existGenre(Long id) throws ErrorDTO;
    GenreDTO saveGenre(GenreDTO dto);
    List<GenreDTO> getAllGenres();
    GenreDTO getGenre(Long id) throws ErrorDTO;
}
