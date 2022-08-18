package com.alkemy.challengejava.service;

import java.util.List;
import java.util.Set;

import com.alkemy.challengejava.dto.ErrorDTO;
import com.alkemy.challengejava.dto.characters.CharacterDTO;

public interface CharacterService {

    // Inserta un Personaje (si no existe ya on es ID)    
    CharacterDTO saveCharacter(CharacterDTO dto);

    // Verifica si existe un Personaje o no (en base a su ID)
    boolean existCharacter(Long id);

    // Devuelve todos los Personajes que estan dados de alta
    List<CharacterDTO> getAllCharacters();

    // Obtiene un Personaje que se le indique (en base a su ID)
    CharacterDTO getCharacter(Long id) throws ErrorDTO;

    // Obtiene una lista de Personaje que cumpla con las condiciones de los filtros
    List<CharacterDTO> getCharacterByFilters(String name,
            Integer age,
            Integer weight,
            Set<Long> movies, String order);


    // Modifica los campos de un Personaje (no el ID)
    void updateCharacter(Long id, CharacterDTO dto) throws ErrorDTO;

    // Da de baja al Personaje que se le indique (mediante su ID)
    void deleteCharacter(Long id) throws ErrorDTO;

}
