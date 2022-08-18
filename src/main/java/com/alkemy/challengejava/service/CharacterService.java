package com.alkemy.challengejava.service;

import java.util.List;
import java.util.Set;

import com.alkemy.challengejava.dto.ErrorDTO;
import com.alkemy.challengejava.dto.characters.CharacterDTO;

public interface CharacterService {

    // Guardar Personaje
    CharacterDTO saveCharacter(CharacterDTO dto);

    // Listar Personajes
    List<CharacterDTO> getAllCharacters();

    CharacterDTO getCharacter(Long id) throws ErrorDTO;

    List<CharacterDTO> getCharacterByFilters(String name,
            Integer age,
            Integer weight,
            Set<Long> movies, String order);

    // Modificar Personaje
    void updateCharacter(Long id, CharacterDTO dto) throws ErrorDTO;

    // Borrar Personaje
    void deleteCharacter(Long id) throws ErrorDTO;

}
