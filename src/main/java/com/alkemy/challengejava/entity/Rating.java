package com.alkemy.challengejava.entity;

public enum Rating {
    INVALID_OPTION("SE INGRESO 0"), // Esto esta para que si el usuario ingresa 0, este esta opcion
    UNA_ESTRELLA("1 ESTRELLA"),
    DOS_ESTRELLAS("2 ESTRELLAS"),
    TRES_ESTRELAS("3 ESTRELLAS"),
    CUATRO_ESTRELLAS("4 ESTRELLAS"),
    CINCO_ESTRELLA("5 ESTRELLAS");

    private String text;

    private Rating(String text){
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}

