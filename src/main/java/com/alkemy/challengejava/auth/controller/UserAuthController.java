package com.alkemy.challengejava.auth.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alkemy.challengejava.auth.dto.UserDTO;
import com.alkemy.challengejava.auth.service.AuthService;
import com.alkemy.challengejava.dto.ErrorDTO;

@Controller
@RequestMapping("/auth") // Es la unica ruta valida para usuarios no autentificados
public class UserAuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody UserDTO user) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(authService.login(user));
        } catch (ErrorDTO e) {
            return e.toResponseEntity();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody UserDTO user) {
        try {
            authService.register(user);
            return ResponseEntity.ok().build();
        } catch (ErrorDTO e) {
            return e.toResponseEntity();
        }
    }
}
