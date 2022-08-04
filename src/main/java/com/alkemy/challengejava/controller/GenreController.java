package com.alkemy.challengejava.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping
    public ResponseEntity<List<GenreDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    // Si indicadara @PostMapping("/otracosa"), para acceer aqui seria POST - /genres/otracosa/
    @PostMapping // POST - /genres
    public ResponseEntity<GenreDTO> save(@RequestBody GenreDTO genre) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(genre));
    }
}