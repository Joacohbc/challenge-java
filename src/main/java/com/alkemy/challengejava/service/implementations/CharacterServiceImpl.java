package com.alkemy.challengejava.service.implementations;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.challengejava.dto.CharacterDTO;
import com.alkemy.challengejava.entity.CharacterEntity;
import com.alkemy.challengejava.mapper.CharacterMapper;
import com.alkemy.challengejava.repository.CharacterRepository;
import com.alkemy.challengejava.service.CharacterService;


@Service
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    private CharacterRepository repository;

    @Autowired
    private CharacterMapper mapper;

    public CharacterDTO saveCharacter(CharacterDTO dto) {
        return mapper.Entity2DTO(repository.save(mapper.DTO2Entity(dto)));
    }

    public List<CharacterDTO> getAllCharacters() {
        return mapper.ListEntity2ListDTO(new HashSet<>(repository.findAll()));
    }

    public boolean deleteCharacter(Long id) {

        if(getCharacter(id) == null){
            return false;
        }

        repository.deleteById(id);
        return true;
    }

    public CharacterDTO getCharacter(Long id) {
        Optional<CharacterEntity> dto = repository.findById(id);
        return dto.isPresent() ? mapper.Entity2DTO(dto.get()) : null;
    }

    public boolean updateCharacter(Long id, CharacterDTO dtoNew) {

        // Intento obtener los datos del Character
        CharacterDTO dto = getCharacter(id);

        // Si no no existe ese Character retorno false
        if(dto == null){
            return false;
        }

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

        // TODO: Hacer algo con el dto.getMovies()
        repository.update(dtoNew.getName(), dtoNew.getAge(), dtoNew.getHistory(), dtoNew.getImage(), dtoNew.getWeight(), id);
        return true;
    }
}
