package edu.fiuba.algo3.modelo;

import java.util.List;

public class Tablero {
    private List<Hexagono> hexagonos;

    public Tablero(List<Hexagono> hexagonos) {
        this.hexagonos = hexagonos;
    }

    public void producir(int numero) {
        for (Hexagono hexagono : hexagonos) {
                hexagono.producirRecursos(numero);
        }
    }

}



