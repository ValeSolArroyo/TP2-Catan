package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.vistas.componentes.VistaTablero;

public class TableroControlador {
    private final Tablero tablero;
    private final VistaTablero vista;

    public TableroControlador(Tablero tablero, VistaTablero vista) {
        this.tablero = tablero;
        this.vista = vista;
    }
}
