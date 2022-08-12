package com.alkemy.challengejava.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Getter;

@Getter
public class ErrorDTO {
    
    private String error;

    private ErrorDTO(String error) {
        this.error = error;
    } 

    /**
     * Rertorna un ResponseEntity<Object> que contiene como unico valor el error del mensaje
     * con el estado 404 NOT_FOUND  
     * @param errorMessage El mensaje de error que se quiere notificar
     * @return ResponseEntity<Object> con el mensaje de error 
     */
    public static ResponseEntity<Object> NotFound(String errorMessage){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(errorMessage));
    }
}
