package com.alkemy.challengejava.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import com.alkemy.challengejava.dto.movies.MovieDTO;
import com.alkemy.challengejava.dto.movies.MovieFiltersDTO;
import com.alkemy.challengejava.service.MovieService;

@Controller
@RequestMapping("movies")
public class MovieController {

    @Autowired
    private MovieService service;

    @GetMapping
    public ResponseEntity<List<MovieDTO>> getAllMovies() {
        return ResponseEntity.ok(service.getAllMovies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getGenre(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.getMovie(id));
        } catch (ErrorDTO e) {
            return e.toResponseEntity();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Map<String, Object>>> getGenresByFilters(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String image,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate creationDateMore,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy")  LocalDate creationDateLess,
            @RequestParam(required = false, defaultValue = "ASC") String order) {
        
                MovieFiltersDTO filts = new MovieFiltersDTO(image, title, creationDateMore, creationDateLess, order);

        // Obtengo los DTOs que pasaron el filtro
        List<MovieDTO> dtos = service.getByFilters(filts);

        // Creo una lista de DTOs que contendra solo Id, Title, Image y CreationDate
        List<Map<String, Object>> list = new LinkedList<>();
        
        for (MovieDTO dto : dtos) {
            Map<String, Object> map = new HashMap<>();
            map.put("creationDate", dto.getCreationDate());
            map.put("image", dto.getImage());
            map.put("title", dto.getTitle());
            map.put("id", dto.getId());
            list.add(map);
        }

        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<Object> saveCharacter(@RequestBody MovieDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.saveMovie(dto));
        } catch (ErrorDTO e) {
            return e.toResponseEntity();
        }
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

    @DeleteMapping("/{idMovie}/characters/{idCharacter}")
    public ResponseEntity<Object> removeCharacter(@PathVariable Long idMovie, @PathVariable Long idCharacter) {
        try {
            service.removeCharacterFromMovie(idMovie, idCharacter);
            return ResponseEntity.noContent().build();
        } catch (ErrorDTO e) {
            return e.toResponseEntity();
        }
    }
}
