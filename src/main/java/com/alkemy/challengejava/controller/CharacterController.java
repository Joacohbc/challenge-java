package com.alkemy.challengejava.controller;

import java.util.List;

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

import com.alkemy.challengejava.dto.CharacterDTO;
import com.alkemy.challengejava.service.CharacterService;

@Controller()
@RequestMapping("characters")
public class CharacterController {

    @Autowired
    private CharacterService service;

    @PostMapping
    public ResponseEntity<CharacterDTO> saveCharacter(@RequestBody CharacterDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto));
    }

    @GetMapping
    ResponseEntity<List<CharacterDTO>> getAllCharaters() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<CharacterDTO> get(@PathVariable Long id) {
        CharacterDTO dto = service.get(id);
        
        // Si DTO exite 
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteACharacter(@PathVariable Long id) {

        // Verifico que personaje exista para borrarlo
        if(service.get(id) == null) {
            return ResponseEntity.badRequest().body("The character with the id "+ id + " does not exist");
        }

        // SI exitia lo borro
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCharacter(@PathVariable Long id, @RequestBody CharacterDTO dto) {

        // Verifico que personaje exista para borrarlo
        if(service.get(id) == null) {
            return ResponseEntity.badRequest().body("The character with the id "+ id + " does not exist");
        }

        service.modify(id, dto);
        return ResponseEntity.noContent().build();
    }
}
