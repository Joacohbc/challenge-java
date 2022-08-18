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

    // Arroja un error al ser ejecutado si el Personaje con Id no existe
    private void throwErrorIfNotExits(Long id){
        if(!existCharacter(id)){
            throw new ErrorDTO("Un personaje con el ID " + id + " no existe", HttpStatus.NOT_FOUND);
        }
    }
    
    @Override
    public boolean existCharacter(Long id) {
        return repository.existsById(id);
    }

    @Override
    public CharacterDTO saveCharacter(CharacterDTO dto) {
        
        if(dto.getId() != null){
            if(existCharacter(dto.getId())){
                throw new ErrorDTO("Un personaje con el ID " + dto.getId() + " ya existe", HttpStatus.BAD_REQUEST);
            }
        }

        // TODO: Validar que los campos no sean nulos al guardar
        CharacterEntity entity = mapper.DTO2Entity(dto,false);
        return mapper.Entity2DTO(repository.save(entity),true);
    }

    @Override
    public List<CharacterDTO> getAllCharacters() {
        return mapper.ListEntity2ListDTO(new HashSet<>(repository.findAll()),true);
    }

    @Override
    public CharacterDTO getCharacter(Long id) throws ErrorDTO {
        throwErrorIfNotExits(id);
        return mapper.Entity2DTO(repository.findById(id).get(), true);
    }

    @Override
    public List<CharacterDTO> getCharacterByFilters(String name, Integer age, Integer weight, Set<Long> movies, String order) {

        // Creo el filtro
        CharacterFiltersDTO filtersDTO = new CharacterFiltersDTO(name, age, weight, movies, order);

        // Obtengo las entidades en base a los filtros
        Set<CharacterEntity> entities = new HashSet<>(repository.findAll(specifications.getSpecsByFilters(filtersDTO)));
        
        // Retorno los resultados
        return mapper.ListEntity2ListDTO(entities, false);
    }
    
    @Override
    public void deleteCharacter(Long id) throws ErrorDTO {
        throwErrorIfNotExits(id);
        repository.deleteById(id);
    }

    @Override
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
}
