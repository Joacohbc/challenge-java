package com.alkemy.challengejava.service;

import java.util.List;

import com.alkemy.challengejava.dto.GenreBasicDTO;


public interface GenreService {
    GenreBasicDTO save(GenreBasicDTO dto);

    List<GenreBasicDTO> getAll();
}
