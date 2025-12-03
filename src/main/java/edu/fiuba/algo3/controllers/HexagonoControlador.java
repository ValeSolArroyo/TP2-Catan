package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.tablero.Hexagono;
import edu.fiuba.algo3.vistas.componentes.ImagenHexagono;

public class HexagonoControlador {
    private final ImagenHexagono vista;
    private Hexagono hexagonoModelo;

    public HexagonoControlador(Hexagono hexagonoModelo, ImagenHexagono vista) {
        this.vista = vista;
        this.hexagonoModelo = hexagonoModelo;
    }
}
