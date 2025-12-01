package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.tablero.Hexagono;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.vistas.VistaTablero;
import edu.fiuba.algo3.vistas.componentes.VistaHexagono;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.List;

public class TableroControlador {
    private final Tablero tablero;
    private final VistaTablero vista;

    public TableroControlador(Tablero tablero, VistaTablero vista) {
        this.tablero = tablero;
        this.vista = vista;

    }

    public VistaTablero getVista() {
        return vista;
    }
}
