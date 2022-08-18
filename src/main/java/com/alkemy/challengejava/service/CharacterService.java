package com.alkemy.challengejava.service;

import java.util.List;
import java.util.Set;

import com.alkemy.challengejava.dto.ErrorDTO;
import com.alkemy.challengejava.dto.characters.CharacterDTO;

public interface CharacterService {

    // Guardar 
    CharacterDTO saveCharacter(CharacterDTO dto);

    // Listar 
    List<CharacterDTO> getAllCharacters();

    CharacterDTO getCharacter(Long id) throws ErrorDTO;

    List<CharacterDTO> getCharacterByFilters(String name,
            Integer age,
            Integer weight,
            Set<Long> movies, String order);

    // Modificar 
    void updateCharacter(Long id, CharacterDTO dto) throws ErrorDTO;

    // Borrar
    void deleteCharacter(Long id) throws ErrorDTO;

}
