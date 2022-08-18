package com.alkemy.challengejava.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alkemy.challengejava.dto.ErrorDTO;
import com.alkemy.challengejava.dto.MovieDTO;
import com.alkemy.challengejava.service.MovieService;

@Controller
@RequestMapping("movies")
public class MovieController {

    @Autowired
    private MovieService service;

    @PostMapping
    public ResponseEntity<Object> saveCharacter(@RequestBody MovieDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.saveMovie(dto));
        } catch (ErrorDTO e) {
            return e.toResponseEntity();
        }
    }

    @GetMapping
    public ResponseEntity<List<MovieDTO>> getAllMovies() {
        return ResponseEntity.ok(service.getAllMovies());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMovie(@PathVariable Long id, @RequestBody MovieDTO movie) {
        try {
            service.updateMovie(id, movie);
            return ResponseEntity.noContent().build();
        } catch (ErrorDTO e) {
            System.out.println(e.toMap());
            return e.toResponseEntity();
        }
    }

    @PostMapping("/{idMovie}/characters/{idCharacter}")
    public ResponseEntity<Object> addCharacter(@PathVariable Long idMovie, @PathVariable Long idCharacter) {
        try {
            service.addCharacterToMovie(idMovie, idCharacter);
            return ResponseEntity.noContent().build();
        } catch (ErrorDTO e) {
            return e.toResponseEntity();
        }
    }
}
