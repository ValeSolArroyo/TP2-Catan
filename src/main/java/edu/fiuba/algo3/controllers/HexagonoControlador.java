package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.tablero.Arista;
import edu.fiuba.algo3.modelo.tablero.Hexagono;
import edu.fiuba.algo3.modelo.tablero.Vertice;
import edu.fiuba.algo3.vistas.componentes.VistaHexagono;
import javafx.scene.control.Button;

public class HexagonoControlador {
    private final VistaHexagono vista;
    private Hexagono hexagonoModelo;

    public HexagonoControlador(Hexagono hexagonoModelo, VistaHexagono vista) {
        this.vista = vista;
        this.hexagonoModelo = hexagonoModelo;
    }

    public void mostrarVertices() {
        vista.mostrarVertices();
    }

    public void mostrarAristas() {
        vista.mostrarAristas();
    }

    public void ocultarVertices() {
        vista.ocultarVertices();
    }

    public void ocultarAristas() {
        vista.ocultarAristas();
    }
}
