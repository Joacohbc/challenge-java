package com.alkemy.challengejava.dto;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Getter;

@Getter
public class ErrorDTO extends RuntimeException{

    private HttpStatus status;

    public ErrorDTO(String error, HttpStatus status) {
        super(error);
        this.status = status;
    } 

    // Retorna un Map que tiene 2 Keys, "Error" y "Status".
    public Map<String, Object> toMap(){
        Map<String, Object> map = new HashMap<>();
        map.put("error", getMessage());
        map.put("status", status.value());
        return map;
    }

    // Retorna un ResponseEntity<Object> que  contiene en el body un Map (obtenido con toMap())
    public ResponseEntity<Object> toResponseEntity() {
        return ResponseEntity.status(status).body(toMap());
    }

    /**
     * Rertorna un ResponseEntity<Object> que contiene como unico valor el error del mensaje
     * con su estado estado (contiene un Map); 
     * @param errorMessage El mensaje de error que se quiere notificar
     * @param status El codigo de estado del error que se quiere notificar
     * @return ResponseEntity<Object> con el mensaje de error 
     */
    public static ResponseEntity<Object> MakeResponseEntity(String errorMessage, HttpStatus status){
        Map<String, Object> err = new HashMap<>();
        err.put("error", errorMessage);
        err.put("status", status);
        return ResponseEntity.status(status).body(err);
    }

}
