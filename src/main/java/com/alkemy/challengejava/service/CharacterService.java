package com.alkemy.challengejava.service;

import java.util.List;

import com.alkemy.challengejava.dto.CharacterDTO;

public interface CharacterService {
    CharacterDTO saveCharacter(CharacterDTO dto);
    boolean updateCharacter(Long id, CharacterDTO dto);
    boolean deleteCharacter(Long id);
    List<CharacterDTO> getAllCharacters();
    CharacterDTO getCharacter(Long id);
}
