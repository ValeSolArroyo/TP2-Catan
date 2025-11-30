package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.tablero.Hexagono;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.vistas.componentes.VistaHexagono;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.List;

public class TableroControlador {
    private Tablero tablero;

    public TableroControlador(Tablero tablero) {
        this.tablero = tablero;
    }

    public Pane mostrarTablero() {
        List<Hexagono> hexagonos = tablero.getHexagonos();
        int[] hexagonosPorFila = {3, 4, 5, 4, 3};

        VBox tableroVista = new VBox(-38);
        tableroVista.setAlignment(Pos.CENTER);
        tableroVista.setPadding(new Insets(15, 80, 0, 5));

        int indiceHexagonos = 0;
        for (int filaHexagonos = 0; filaHexagonos < hexagonosPorFila.length; filaHexagonos++) {
            int cantHexagonosPorFila = hexagonosPorFila[filaHexagonos];

            HBox fila = new HBox(-5);
            fila.setAlignment(Pos.CENTER);
            for (int i = 0; i < cantHexagonosPorFila; i++) {
                VistaHexagono vista = new VistaHexagono(hexagonos.get(indiceHexagonos++));
                fila.getChildren().add(vista);
            }

            tableroVista.getChildren().add(fila);
        }
        return tableroVista;
    }
}
