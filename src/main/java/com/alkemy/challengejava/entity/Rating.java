package com.alkemy.challengejava.entity;

public enum Rating {
    UNA_ESTRELLAS(1),
    DOS_ESTRELLAS(2),
    TRES_ESTRELAS(3),
    CUATRO_ESTRELLAS(4),
    CINCO_ESTRELLA(5);

    private int number;

    public int getNumber() {
        return this.number;
    }

    private Rating(int number){
        this.number = number;
    }
}

