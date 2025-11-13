package edu.fiuba.algo3.modelo;

public class Ladron {
    // ATRIBUTOS DE CLASE

    // ATRIBUTOS
    private Hexagono ubicacion;

    // CONSTRUCTORES

    // MÉTODOS DE CLASE

    // MÉTODOS GENERALES

    // MÉTODOS DE COMPORTAMIENTO
    public void moverA(Hexagono nuevoHexagono) {
        this.ubicacion = nuevoHexagono;
    }

    public boolean estaEn(Hexagono hexagono) {
        return this.ubicacion != null && this.ubicacion.equals(hexagono);
    }

    // GETTERS
    public Hexagono obtenerUbicacion() {
        return ubicacion;
    }

    // SETTERS
}
