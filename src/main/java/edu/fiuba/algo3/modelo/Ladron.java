package edu.fiuba.algo3.modelo;

public class Ladron {
    private Hexagono ubicacion;

    public void moverA(Hexagono nuevoHexagono) {
        this.ubicacion = nuevoHexagono;
    }

    public boolean estaEn(Hexagono hexagono) {
        return this.ubicacion != null && this.ubicacion.equals(hexagono);
    }

    public Hexagono getUbicacion() {
        return ubicacion;
    }
}
