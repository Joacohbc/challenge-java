package com.alkemy.challengejava.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.alkemy.challengejava.auth.dto.UserDTO;
import com.alkemy.challengejava.dto.ErrorDTO;

@Service
public class AuthService {

    @Autowired
    private UserDetailsCustomSerivce services;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    public void register(UserDTO userDTO) throws ErrorDTO {
        try {
            services.save(userDTO);
        } catch (Exception e) {
            throw new ErrorDTO("Ocurrio un error al registrarse: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public String login(UserDTO userDto) throws ErrorDTO {
        try {
            // Verifico si el un usuario valido
            Authentication auth = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));

            // Si el usuario es valido, obtengo su datos
            UserDetails user = (UserDetails) auth.getPrincipal();

            // Y le retorno el Token creado con los datos del usuario
            return jwtUtils.generateToken(user);

        } catch (Exception e) {
            throw new ErrorDTO("Usuario o Contraseña incorrectos", HttpStatus.BAD_REQUEST);
        }
    }
}
