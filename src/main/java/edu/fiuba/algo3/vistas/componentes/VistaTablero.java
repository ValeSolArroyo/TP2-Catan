package edu.fiuba.algo3.vistas.componentes;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import javafx.scene.layout.StackPane;


public class VistaTablero extends StackPane {
    private VistaHexagonos vistaHexagonos;
    private VistaVertices vistaVertices;
    private VistaAristas vistaAristas;

    public VistaTablero(Tablero tablero) {
        vistaHexagonos = new VistaHexagonos(tablero.getHexagonos());
        vistaVertices = new VistaVertices(tablero.getVertices());
        vistaAristas  = new VistaAristas(tablero.getAristas());

        this.getChildren().addAll(vistaHexagonos, vistaVertices, vistaAristas);
    }
}
