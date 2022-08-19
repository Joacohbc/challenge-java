package com.alkemy.challengejava.auth.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    @Email(message = "El correo electronico ingresado es invalido")
    private String username;

    @Size(min = 8, message = "La contraseña debe tener por lo menos 8 caracteres")
    private String password;
    
}
