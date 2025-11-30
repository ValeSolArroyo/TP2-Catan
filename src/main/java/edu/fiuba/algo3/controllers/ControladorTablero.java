package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.tablero.Hexagono;
import edu.fiuba.algo3.modelo.tablero.Tablero;

import java.util.List;

public class ControladorTablero {
    private Tablero tablero;

    public ControladorTablero(Tablero tablero) {
        this.tablero = tablero;
    }


    public void mostrarTablero() {
        List<Hexagono> hexagonos = tablero.getHexagonos();

        for (Hexagono hexagono: hexagonos){

        }
            
    }
}
