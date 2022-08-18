package com.alkemy.challengejava.service.implementations;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.alkemy.challengejava.dto.ErrorDTO;
import com.alkemy.challengejava.dto.characters.CharacterDTO;
import com.alkemy.challengejava.dto.characters.CharacterFiltersDTO;
import com.alkemy.challengejava.entity.CharacterEntity;
import com.alkemy.challengejava.mapper.CharacterMapper;
import com.alkemy.challengejava.repository.characters.CharacterRepository;
import com.alkemy.challengejava.repository.characters.CharacterSpecification;
import com.alkemy.challengejava.service.CharacterService;


@Service
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    private CharacterRepository repository;

    @Autowired
    private CharacterMapper mapper;

    @Autowired
    private CharacterSpecification specifications;

    public CharacterDTO saveCharacter(CharacterDTO dto) {
        CharacterEntity entity = mapper.DTO2Entity(dto,false);
        return mapper.Entity2DTO(repository.save(entity),true);
    }

    public List<CharacterDTO> getAllCharacters() {
        return mapper.ListEntity2ListDTO(new HashSet<>(repository.findAll()),true);
    }

    public CharacterDTO getCharacter(Long id) throws ErrorDTO {
        Optional<CharacterEntity> dto = repository.findById(id);

        if(!dto.isPresent()){
            throw new ErrorDTO("The character with the id " + id + " does not exist", HttpStatus.NOT_FOUND);
        }

        return mapper.Entity2DTO(dto.get(), true);
    }

    public void deleteCharacter(Long id) throws ErrorDTO {
        getCharacter(id);
        repository.deleteById(id);
    }

    public void updateCharacter(Long id, CharacterDTO dtoNew) throws ErrorDTO {

        // Intento obtener los datos del Character
        CharacterDTO dto = getCharacter(id);

        // Si algun campo del DTO para modificar esta vacio 
        // le setteo el valor que ya tiene la base de datos

        if(Strings.isBlank(dtoNew.getImage())){
            dtoNew.setImage(dto.getImage());
        }

        if(Strings.isBlank(dtoNew.getName())){
            dtoNew.setName(dto.getName());
        }

        if(dtoNew.getAge() == 0){
            dtoNew.setAge(dto.getAge());
        }

        if(dtoNew.getWeight() == 0){
            dtoNew.setWeight(dto.getWeight());
        }

        if(Strings.isBlank(dtoNew.getHistory())){
            dtoNew.setHistory(dto.getHistory());
        }
        
        repository.update(dtoNew.getName(), dtoNew.getAge(), dtoNew.getHistory(), dtoNew.getImage(), dtoNew.getWeight(), id);
    }

    public List<CharacterDTO> getCharacterByFilters(String name, Integer age, Integer weight, Set<Long> movies, String order) {
        CharacterFiltersDTO filtersDTO = new CharacterFiltersDTO(name, age, weight, movies, order);

        Set<CharacterEntity> entities = new HashSet<>(repository.findAll(specifications.getSpecsByFilters(filtersDTO)));
        return mapper.ListEntity2ListDTO(entities, true);
    }
}
