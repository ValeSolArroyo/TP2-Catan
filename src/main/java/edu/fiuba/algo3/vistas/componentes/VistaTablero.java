package edu.fiuba.algo3.vistas.componentes;

import edu.fiuba.algo3.modelo.tablero.Hexagono;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.List;

public class VistaTablero extends StackPane {
    private VistaHexagonos vistaHexagonos;
    private VistaVertices vistaVertices;
    private VistaAristas vistaAristas;

    private List<ImagenHexagono> vistasHexagonos = new ArrayList<>();

    public VistaTablero(Tablero tablero) {
        vistaHexagonos = new VistaHexagonos(tablero.getHexagonos());
        //vistaAristas  = new VistaAristas(tablero.getAristas());
        //vistaVertices = new VistaVertices(tablero.getVertices());

        this.getChildren().add(vistaHexagonos);
        //, vistaAristas,vistaVertices
    }
}
