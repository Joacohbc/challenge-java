package com.alkemy.challengejava.service;

import java.util.List;

import com.alkemy.challengejava.dto.GenreDTO;


public interface GenreService {
    GenreDTO save(GenreDTO dto);

    List<GenreDTO> getAll();
}
