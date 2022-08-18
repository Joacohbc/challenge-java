package com.alkemy.challengejava.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alkemy.challengejava.dto.ErrorDTO;
import com.alkemy.challengejava.dto.characters.CharacterDTO;
import com.alkemy.challengejava.service.CharacterService;

@Controller
@RequestMapping("characters")
public class CharacterController {

    @Autowired
    private CharacterService service;

    @PostMapping // POST - /characters
    public ResponseEntity<CharacterDTO> saveCharacter(@RequestBody CharacterDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveCharacter(dto));
    }

    @GetMapping // GET - /characters
    ResponseEntity<List<CharacterDTO>> getAllCharaters() {
        return ResponseEntity.ok(service.getAllCharacters());
    }

    @GetMapping("/{id}") // GET - /characters/{id}
    ResponseEntity<Object> get(@PathVariable Long id) {
        try {
            // Obtengo el el DTO de la base de datos
            CharacterDTO dto = service.getCharacter(id);
            return ResponseEntity.ok(dto);

        } catch (ErrorDTO e) {
            return e.toResponseEntity();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<CharacterDTO>> getCharacterByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) Integer weight,
            @RequestParam(required = false) Set<Long> movies,
            @RequestParam(required = false, defaultValue = "ASC") String order) {

        return ResponseEntity.ok(service.getCharacterByFilters(name, age, weight, movies, order));
    }

    @DeleteMapping("/{id}") // DELETE - /characters/{id}
    public ResponseEntity<Object> deleteCharacter(@PathVariable Long id) {
        try {
            service.deleteCharacter(id);
            return ResponseEntity.noContent().build();
        } catch (ErrorDTO e) {
            return e.toResponseEntity();
        }
    }

    @PutMapping("/{id}") // PUT - /characters/{id}
    public ResponseEntity<Object> updateCharacter(@PathVariable Long id, @RequestBody CharacterDTO dto) {
        try {
            service.updateCharacter(id, dto);
            return ResponseEntity.noContent().build();
        } catch (ErrorDTO e) {
            return e.toResponseEntity();
        }
    }
}
