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
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveCharacter(dto));
    }

    @GetMapping
    ResponseEntity<List<CharacterDTO>> getAllCharaters() {
        return ResponseEntity.ok(service.getAllCharacters());
    }

    @GetMapping("/{id}")
    ResponseEntity<CharacterDTO> get(@PathVariable Long id) {

        // Obtengo el el DTO de la base de datos
        CharacterDTO dto = service.getCharacter(id);
        
        // Si DTO exite (es diferente de null) lo envio con Status.OK y si no le envio un NotFound
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCharacter(@PathVariable Long id) {
        
        // Si no se borro el carcter (false) le envio un error al usuario 
        if(!service.deleteCharacter(id)){
            return ResponseEntity.badRequest().body("The character with the id "+ id + " does not exist");
        }

        // Si se borro el personaje lo notifico
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCharacter(@PathVariable Long id, @RequestBody CharacterDTO dto) {

        // Si no se pudo modificar del personaje (false) le envio un error al usuario 
        if(!service.updateCharacter(id, dto)) {
            return ResponseEntity.badRequest().body("The character with the id "+ id + " does not exist");
        }

        // Si se modifico con exito lo notifico
        return ResponseEntity.noContent().build();
    }
}
