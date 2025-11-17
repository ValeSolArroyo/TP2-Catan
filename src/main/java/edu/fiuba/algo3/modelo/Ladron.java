package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.patronHexagono.Hexagono;

public class Ladron {
    private Hexagono ubicacion;

    public void moverA(Hexagono nuevoHexagono) {
        this.ubicacion = nuevoHexagono;
    }

    public boolean estaEn(Hexagono hexagono) {
        return this.ubicacion != null && this.ubicacion.equals(hexagono);
    }

    public Hexagono obtenerUbicacion() {
        return ubicacion;
    }
}
