package com.alkemy.challengejava.service;

import java.util.List;

import com.alkemy.challengejava.dto.CharacterDTO;

public interface CharacterService {
    CharacterDTO save(CharacterDTO dto);
    CharacterDTO modify(Long id, CharacterDTO dto);
    void delete(Long id);
    List<CharacterDTO> getAll();
    CharacterDTO get(Long id);
}
