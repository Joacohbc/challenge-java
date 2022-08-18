package com.alkemy.challengejava.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.challengejava.dto.GenreDTO;
import com.alkemy.challengejava.service.GenreService;

@RestController // Le indico a SpringBoot que estos una clase Controller
@RequestMapping("genres") // Y que el URL del endpoint es /genres
public class GenreController {
    
    @Autowired // Le digo a SpringBoot que me inicialize el Servicio automaticamente
    private GenreService service;

    @GetMapping // GET - /genres
    public ResponseEntity<List<GenreDTO>> getAllGenres() {
        return ResponseEntity.ok(service.getAllGenres());
    }

    @GetMapping("/{id}") // GET - /genres
    public ResponseEntity<GenreDTO> getGenre(@PathVariable Long id) {
        return ResponseEntity.ok(service.getGenre(id));
    }

    // Si indicadara @PostMapping("/otracosa"), para acceer aqui seria POST - /genres/otracosa/
    @PostMapping // POST - /genres
    public ResponseEntity<GenreDTO> save(@RequestBody GenreDTO genre) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveGenre(genre));
    }
}