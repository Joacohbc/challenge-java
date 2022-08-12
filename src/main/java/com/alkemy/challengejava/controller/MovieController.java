package com.alkemy.challengejava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alkemy.challengejava.dto.MovieDTO;
import com.alkemy.challengejava.service.MovieService;

@Controller
@RequestMapping("movies")
public class MovieController {

    @Autowired
    private MovieService service;
    
    @PostMapping
    public ResponseEntity<MovieDTO> saveCharacter(@RequestBody MovieDTO dto){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.saveMovie(dto));
    }
}
