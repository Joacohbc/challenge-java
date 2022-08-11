package com.alkemy.challengejava.service.implementations;

import java.util.List;
import java.util.Optional;

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

    public CharacterDTO save(CharacterDTO dto) {
        return mapper.Entity2DTO(repository.save(mapper.DTO2Entity(dto)));
    }

    public List<CharacterDTO> getAll() {
        return mapper.ListEntity2ListDTO(repository.findAll());
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public CharacterDTO get(Long id) {
        Optional<CharacterEntity> dto = repository.findById(id);
        return dto.isPresent() ? mapper.Entity2DTO(dto.get()) : null;
    }

    public void modify(Long id, CharacterDTO dto) {
        repository.update(dto.getName(), dto.getAge(), dto.getHistory(), dto.getImage(), dto.getWeight(), id);
    }
}
