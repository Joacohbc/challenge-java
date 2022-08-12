package com.alkemy.challengejava.service;

import java.util.List;
import java.util.Set;

import com.alkemy.challengejava.dto.characters.CharacterDTO;

public interface CharacterService {
    CharacterDTO saveCharacter(CharacterDTO dto);

    boolean updateCharacter(Long id, CharacterDTO dto);

    boolean deleteCharacter(Long id);

    List<CharacterDTO> getAllCharacters();

    CharacterDTO getCharacter(Long id);

    List<CharacterDTO> getCharacterByFilters(String name,
            Integer age,
            Integer weight,
            Set<Long> movies, String order);
}
