package com.alkemy.challengejava.auth.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.alkemy.challengejava.auth.dto.UserDTO;
import com.alkemy.challengejava.auth.entity.UserEntity;
import com.alkemy.challengejava.auth.repository.UserRepository;
import com.alkemy.challengejava.dto.ErrorDTO;

// Implemento la Interface de UserDetailsService ya que es la interface
// predeterminada de para el servicio de usuarios (UserDetails)
@Service
public class UserDetailsCustomSerivce implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws ErrorDTO {
        // Busco el usuario en la base de datos
        UserEntity user = repository.findByUsername(username);
        
        // Si no existe arrojo un error del error
        if (user == null) { 
            throw new ErrorDTO("No existe un usuario con ese nombre", HttpStatus.NOT_FOUND);
        }
        
        return user;
    }

    public void save(@Valid UserDTO dto) throws Exception {
        UserEntity user = new UserEntity();

        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setAccountEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        repository.save(user);
    }
}
