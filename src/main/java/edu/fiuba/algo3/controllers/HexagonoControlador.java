package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.vistas.componentes.VistaHexagono;

public class HexagonoControlador {
    private final VistaHexagono vista;

    public HexagonoControlador(VistaHexagono vista) {
        this.vista = vista;
    }

    public void mostrarVertices() {
        vista.mostrarVertices();
    }

    public void mostrarAristas() {
        vista.mostrarAristas();
    }

    public void ocultarTodo() {
        vista.ocultarVertices();
        vista.ocultarAristas();
    }
}
